package com.kmp.movieapp.core.util.permission.speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import android.speech.SpeechRecognizer as AndroidSpeechRecognizer

actual class SpeechRecognizer(private val context: Context) {

    private var recognizer: AndroidSpeechRecognizer? = null

    actual fun startListening(): Flow<SpeechResult> = callbackFlow {
        recognizer = AndroidSpeechRecognizer.createSpeechRecognizer(context)

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }

        recognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onPartialResults(partialResults: Bundle?) {
                val partial = partialResults
                    ?.getStringArrayList(AndroidSpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull() ?: return
                trySend(SpeechResult.Partial(partial))
            }

            override fun onResults(results: Bundle?) {
                val text = results
                    ?.getStringArrayList(AndroidSpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull() ?: ""
                trySend(SpeechResult.Final(text))
                close()
            }

            override fun onError(error: Int) {
                if (error == AndroidSpeechRecognizer.ERROR_NO_MATCH) {
                    trySend(SpeechResult.NoMatch)
                } else {
                    trySend(SpeechResult.Stopped)
                }
                close()
            }

            override fun onEndOfSpeech() {
                trySend(SpeechResult.Stopped)
            }

            // Nicht benötigt aber müssen implementiert werden
            override fun onReadyForSpeech(params: Bundle?) = Unit
            override fun onBeginningOfSpeech() = Unit
            override fun onRmsChanged(rmsdB: Float) = Unit
            override fun onBufferReceived(buffer: ByteArray?) = Unit
            override fun onEvent(eventType: Int, params: Bundle?) = Unit
        })

        recognizer?.startListening(intent)

        awaitClose { stopListening() }
    }

    actual fun stopListening() {
        recognizer?.stopListening()
        recognizer?.destroy()
        recognizer = null
    }

    actual fun isAvailable(): Boolean =
        AndroidSpeechRecognizer.isRecognitionAvailable(context)
}