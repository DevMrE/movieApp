package com.kmp.movieapp.homescreen.model

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class UiTabState(
    val movieTabResource: StringResource,
    val seriesTabResource: StringResource,
)
