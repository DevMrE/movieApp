package com.kmp.movieapp.core.util.permission.speech

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class SpeechRecognizer {

    actual fun startListening(): Flow<SpeechResult> = flowOf(SpeechResult.Stopped)

    actual fun stopListening() = Unit

    actual fun isAvailable(): Boolean = false
}