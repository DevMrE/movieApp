package com.kmp.movieapp.core.util.permission.speech

import com.kmp.movieapp.core.util.boolean.isTrue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.AVFAudio.AVAudioEngine
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Speech.SFSpeechAudioBufferRecognitionRequest
import platform.Speech.SFSpeechRecognitionTask
import platform.Speech.SFSpeechRecognizer

actual class SpeechRecognizer {

    private var recognitionTask: SFSpeechRecognitionTask? = null
    private var audioEngine: AVAudioEngine? = null

    @OptIn(ExperimentalForeignApi::class)
    actual fun startListening(): Flow<SpeechResult> = callbackFlow {
        val recognizer = SFSpeechRecognizer(locale = NSLocale.currentLocale)
        val engine = AVAudioEngine()
        audioEngine = engine

        val request = SFSpeechAudioBufferRecognitionRequest().apply {
            shouldReportPartialResults = true
        }

        val inputNode = engine.inputNode
        val format = inputNode.outputFormatForBus(0u)

        inputNode.installTapOnBus(
            bus = 0u,
            bufferSize = 1024u,
            format = format
        ) { buffer, _ ->
            buffer?.let { request.appendAudioPCMBuffer(it) }
        }

        engine.prepare()
        engine.startAndReturnError(null)

        recognitionTask = recognizer.recognitionTaskWithRequest(
            request = request,
            resultHandler = { result, error ->
                if (error != null) {
                    trySend(SpeechResult.Stopped)
                    close()
                    return@recognitionTaskWithRequest
                }

                result?.let {
                    val text = it.bestTranscription.formattedString
                    if (it.isFinal()) {
                        trySend(SpeechResult.Final(text))
                        close()
                    } else {
                        trySend(SpeechResult.Partial(text))
                    }
                }
            }
        )

        awaitClose { stopListening() }
    }

    actual fun stopListening() {
        audioEngine?.inputNode?.removeTapOnBus(0u)
        audioEngine?.stop()
        audioEngine = null
        recognitionTask?.cancel()
        recognitionTask = null
    }

    actual fun isAvailable(): Boolean =
        SFSpeechRecognizer(locale = NSLocale.currentLocale).isAvailable().isTrue
}