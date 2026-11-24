package com.kmp.movieapp.overview.presentation

import com.kmp.movieapp.model.UiMediaCard

data class OverviewUiState(
    val contentList: List<UiMediaCard> = emptyList()
)
