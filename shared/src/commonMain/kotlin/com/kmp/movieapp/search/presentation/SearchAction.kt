package com.kmp.movieapp.search.presentation

import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.presentation.model.filter.UiGenre

sealed interface SearchAction {

    data class OnSearchUpdated(val query: String?) : SearchAction

    data class OnContentClicked(val uiMediaCard: UiMediaCard) : SearchAction

    data class OnGenreUpdated(val genre: UiGenre) : SearchAction

    data object OnLoadNextPage : SearchAction

}