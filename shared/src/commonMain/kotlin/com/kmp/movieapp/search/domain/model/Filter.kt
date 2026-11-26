package com.kmp.movieapp.search.domain.model

data class Filter(
    val page: Int,
    val query: String? = null,
    val filterType: FilterType? = null,
    val searchResult: List<FilteredContent>? = null,
)
