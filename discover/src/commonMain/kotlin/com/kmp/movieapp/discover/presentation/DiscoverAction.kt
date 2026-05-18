package com.kmp.movieapp.discover.presentation

import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.discover.presentation.model.UiFilter

sealed interface DiscoverAction {

    data class OnFilterClicked(val uiFilter: UiFilter) : DiscoverAction

    data class OnContentClicked(val uiMediaCard: UiMediaCard) : DiscoverAction

}