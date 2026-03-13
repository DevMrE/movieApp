package com.kmp.movieapp.search.presentation.model

internal data class UiSearchState(
    val search: String = "",
    val isSearchActive: Boolean = false,
    val searchResults: List<UiSearch> = emptyList()
)
