package com.kmp.movieapp.search.presentation.model

import com.kmp.movieapp.search.presentation.model.filter.UiFilterType
import com.kmp.movieapp.core.ui.content.model.UiMediaCard

data class UiBrowse(
    val page: Int = 1,
    val search: String? = null,
    val contentList: List<UiMediaCard> = emptyList(),
    val genreFilter: UiFilterType.Genre? = null,
)