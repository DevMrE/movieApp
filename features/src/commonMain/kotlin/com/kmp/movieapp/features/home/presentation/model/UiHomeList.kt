package com.kmp.movieapp.features.home.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.features.home.domain.model.HomeCategory

@Stable
internal data class UiHomeList(
    val category: HomeCategory,
    val movies: List<UiMedia>,
)
