package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

@Stable
data class UiPopularMovies(
    val isLoading: Boolean = false,
    val movieList: List<UiMediaCard> = emptyList()
)
