package com.kmp.movieapp.app.search.model

data class UiSearchState(
    val search: String = "",
    val isSearchActive: Boolean = false,
    val searchResults: List<String> = emptyList()
)
