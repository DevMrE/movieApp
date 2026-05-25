package com.kmp.movieapp.browse.model

sealed interface UiFilterType {

    data class SortByASC(
        val asc: Boolean = true
    ) : UiFilterType

    data class ByGenre(
        val genres: List<UiGenre> = emptyList()
    ) : UiFilterType
}