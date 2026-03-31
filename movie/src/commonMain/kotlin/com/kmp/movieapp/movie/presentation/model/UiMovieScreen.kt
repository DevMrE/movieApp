package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable

@Stable
internal data class UiMovieScreen(
    val isLoading: Boolean = true,
    val nowPlaying: UiMovieList?,
    val popularMovie: UiMovieList?,
    val topRatedMovies: UiMovieList?,
)