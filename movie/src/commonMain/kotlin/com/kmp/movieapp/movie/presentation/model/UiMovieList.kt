package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable

@Stable
data class UiMovieList(
    val title: String?,
    val scrollingIndex: Int = 0,
    val movies: List<UiMovie>,
)
