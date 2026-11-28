package com.kmp.movieapp.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory

@Stable
data class UiMediaCard(
    val id: String,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val type: MediaCategory
)
