package com.kmp.movieapp.navigation.home.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.ui.content.model.UiSection

@Stable
internal data class UiHomeData(
    val isLoading: Boolean = true,
    val trendingList: UiSection?,
    val popularMovie: UiSection?,
    val popularSeries: UiSection?
)