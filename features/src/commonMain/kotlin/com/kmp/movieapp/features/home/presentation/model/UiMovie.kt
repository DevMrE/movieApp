package com.kmp.movieapp.features.home.presentation.model

import androidx.compose.runtime.Stable

/**
 * Represents a single movie for the movie list screen
 */
@Stable
internal data class UiMovie(
    val id: String,
    val title: String,
    val genre: String,
    val posterPath: String?,
    val backdropPath: String?,
)