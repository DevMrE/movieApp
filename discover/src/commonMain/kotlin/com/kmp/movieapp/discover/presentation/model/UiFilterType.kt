package com.kmp.movieapp.discover.presentation.model

sealed interface UiFilterType {

    data class SortByASC(
        val asc: Boolean = true
    ) : UiFilterType

    data class ByGenre(
        val genres: List<UiGenre> = emptyList()
    ) : UiFilterType
}