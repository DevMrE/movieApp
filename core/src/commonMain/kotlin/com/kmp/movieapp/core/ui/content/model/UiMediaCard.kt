package com.kmp.movieapp.core.ui.content.model

import androidx.compose.runtime.Stable

@Stable
data class UiMediaCard(
    val id: String,
    val title: String,
    val bigCard: Boolean = false,
    val posterPath: String?,
    val backdropPath: String?,
    val type: MediaCategory
)
