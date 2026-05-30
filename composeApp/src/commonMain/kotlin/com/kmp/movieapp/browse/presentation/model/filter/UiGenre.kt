package com.kmp.movieapp.browse.presentation.model.filter

data class UiGenre(
    val id: String,
    val name: String,
    val selected: Boolean = false,
    val uiGenreType: UiGenreType = UiGenreType.MOVIE
): UiFilterKey