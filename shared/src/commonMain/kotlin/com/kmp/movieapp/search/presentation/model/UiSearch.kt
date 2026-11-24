package com.kmp.movieapp.search.presentation.model

import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.presentation.model.filter.UiFilterType

data class UiSearch(
    val page: Int = 1,
    val search: String? = null,
    val contentList: List<UiMediaCard> = emptyList(),
    val genreFilter: UiFilterType.Genre? = null,
)