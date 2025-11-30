package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable

/**
 * Represents an single movie for the movie list screen
 */
@Stable
data class UiMovie(
    val id: Int,
    val title: String,
    val genre: String,
    val posterPath: String?,
)