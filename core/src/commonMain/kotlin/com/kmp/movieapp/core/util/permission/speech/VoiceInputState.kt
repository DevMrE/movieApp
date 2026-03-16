package com.kmp.movieapp.core.util.permission.speech

data class VoiceInputState(
    val text: String = "",
    val isListening: Boolean = false,
    val partialText: String = "",
    val error: String? = null
)