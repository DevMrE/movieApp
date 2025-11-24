package com.kmp.movieapp.movie.presentation.model

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class UiMovieList(
    val titleRes: StringResource?,
    val scrollingIndex: Int = 0,
    val movies: List<UiMovie>,
)
