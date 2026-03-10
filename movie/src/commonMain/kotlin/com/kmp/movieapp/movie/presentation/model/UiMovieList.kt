package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.movie.domain.model.MovieCategory

@Stable
data class UiMovieList(
    val category: MovieCategory,
    val movies: List<UiMovie>,
)
