package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable

/**
 * Represents a single movie for the movie list screen
 */
@Stable
data class UiMovie(
    val title: String,
    val genre: String,
    val posterPath: String?,
    val backdropPath: String?,
)