package com.kmp.movieapp.core.util.permission.speech

import kotlinx.coroutines.flow.Flow

sealed interface SpeechResult {
    data class Partial(val text: String) : SpeechResult   // Zwischenergebnis während Sprechen
    data class Final(val text: String) : SpeechResult     // Endergebnis
    data object NoMatch : SpeechResult                     // Nichts erkannt
    data object Stopped : SpeechResult                     // Aufnahme gestoppt
}

expect class SpeechRecognizer {

    fun startListening(): Flow<SpeechResult>
    fun stopListening()
    fun isAvailable(): Boolean
}