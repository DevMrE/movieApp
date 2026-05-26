package com.kmp.movieapp.browse.model.filter

data class UiGenre(
    val id: String,
    val name: String,
    val selected: Boolean = false
): UiFilterKey