package com.kmp.movieapp.search.presentation.mapper

import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.presentation.SearchViewModel
import com.kmp.movieapp.search.presentation.model.UiSearch

internal fun Search.toUiData() = UiSearch(
    title = titleInfo.mainTitle,
    posterPath = media.posterUrl ?: ""
)

context(viewModel: SearchViewModel)
internal fun Iterable<Search>?.mapToUiData(): List<UiSearch> =
    this?.map { it.toUiData() } ?: emptyList()
