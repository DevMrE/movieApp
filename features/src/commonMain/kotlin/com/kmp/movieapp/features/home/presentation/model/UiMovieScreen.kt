package com.kmp.movieapp.features.home.presentation.model

import androidx.compose.runtime.Stable

@Stable
internal data class UiMovieScreen(
    val isLoading: Boolean = true,
    val trendingList: UiHomeList?,
    val popularMovie: UiHomeList?,
    val popularSeries: UiHomeList?
)