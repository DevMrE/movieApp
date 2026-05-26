package com.kmp.movieapp.browse

import com.kmp.movieapp.browse.model.filter.UiFilterKey
import com.kmp.movieapp.browse.model.filter.UiGenre
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

sealed interface BrowseAction {

    data class OnFilterClicked(val filterKey: UiFilterKey) : BrowseAction

    data class OnContentClicked(val uiMediaCard: UiMediaCard) : BrowseAction

    data class OnGenreUpdated(val genre: UiGenre): BrowseAction

}