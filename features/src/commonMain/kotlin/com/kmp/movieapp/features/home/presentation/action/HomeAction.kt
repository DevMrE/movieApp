package com.kmp.movieapp.features.home.presentation.action

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.features.home.presentation.model.HomeCategory

internal sealed interface HomeAction {

    data class OnNavigateToDetailScreen(val id: String, val mediaCategory: MediaCategory) : HomeAction

    data class OnSeeAllClicked(val homeCategory: HomeCategory?) : HomeAction

    data object OnRefresh : HomeAction
}