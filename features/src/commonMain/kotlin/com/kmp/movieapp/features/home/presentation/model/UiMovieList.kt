package com.kmp.movieapp.features.home.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.features.home.domain.model.MovieCategory

@Stable
internal data class UiMovieList(
    val category: MovieCategory,
    val movies: List<UiMovie>,
)
