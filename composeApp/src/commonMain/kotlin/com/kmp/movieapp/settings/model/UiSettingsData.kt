package com.kmp.movieapp.settings.model

import com.kmp.movieapp.core.util.permission.speech.VoiceInputState

data class UiSettingsData(
    val permissionDemoResult: PermissionDemoResult? = null,
    val voiceInputState: VoiceInputState? = null
)
