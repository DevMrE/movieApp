package com.kmp.movieapp.homescreen.presentation.action

import com.kmp.movieapp.core.ui.content.model.MediaCategory

internal sealed interface HomeAction {

    data class OnNavigateToDetailScreen(val id: String, val mediaCategory: MediaCategory) : HomeAction

    data class OnSeeAllClicked(val mediaCategory: MediaCategory?) : HomeAction

    data object OnRefresh : HomeAction
}