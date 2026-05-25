package com.kmp.movieapp.browse

import com.kmp.movieapp.browse.model.UiFilter
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

sealed interface BrowseAction {

    data class OnFilterClicked(val uiFilter: UiFilter) : BrowseAction

    data class OnContentClicked(val uiMediaCard: UiMediaCard) : BrowseAction

}