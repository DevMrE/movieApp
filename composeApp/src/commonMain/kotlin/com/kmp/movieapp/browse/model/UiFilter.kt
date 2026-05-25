package com.kmp.movieapp.browse.model

data class UiFilter(
    val name: String,
    val filterType: UiFilterType,
    val isSeclected: Boolean = false
)
