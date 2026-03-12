package com.kmp.movieapp.app.search.action

sealed interface SearchAction {
    data class OnSearchChanged(val query: String) : SearchAction
    data object OnSearchActiveChanged : SearchAction
}