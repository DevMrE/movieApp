package com.kmp.movieapp.search.presentation.action

sealed interface SearchAction {
    data class OnSearchChanged(val query: String) : SearchAction
    data object OnSearchActiveChanged : SearchAction
}