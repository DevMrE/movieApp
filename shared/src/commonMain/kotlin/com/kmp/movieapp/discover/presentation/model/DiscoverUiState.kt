package com.kmp.movieapp.discover.presentation.model

import androidx.compose.runtime.Stable

@Stable
internal data class DiscoverUiState(
    val isLoading: Boolean = true,
    val contentSections: Set<DiscoverSection>? = null
)