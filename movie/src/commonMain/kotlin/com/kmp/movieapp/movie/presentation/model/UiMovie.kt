package com.kmp.movieapp.movie.presentation.model

/**
 * Represents an single movie for the movie list screen
 */
data class UiMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val genre: String
)