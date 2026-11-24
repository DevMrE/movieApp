package com.kmp.movieapp.discover.presentation

import com.kmp.movieapp.core.ui.content.model.MediaCategory

internal sealed interface DiscoverAction {

    data class OnNavigateToDetailScreen(
        val id: String,
        val mediaCategory: MediaCategory
    ) : DiscoverAction

    data class OnSeeAllClicked(val mediaCategory: MediaCategory) : DiscoverAction

    data object OnRefresh : DiscoverAction

    data object NavigateBack : DiscoverAction
}