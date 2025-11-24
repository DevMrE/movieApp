package com.kmp.movieapp.movie.presentation.model

data class UiMovieScreen(
    val isLoading: Boolean = true,
    val nowPlaying: UiMovieList,
    val popularMovie: UiMovieList,
    val topRatedMovies: UiMovieList,
)