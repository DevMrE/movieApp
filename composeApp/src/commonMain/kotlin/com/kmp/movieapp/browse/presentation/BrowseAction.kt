package com.kmp.movieapp.browse.presentation

import com.kmp.movieapp.browse.presentation.model.filter.UiGenre
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

sealed interface BrowseAction {

    data class OnSearchUpdated(val query: String?) : BrowseAction

    data class OnContentClicked(val uiMediaCard: UiMediaCard) : BrowseAction

    data class OnGenreUpdated(val genre: UiGenre) : BrowseAction

}