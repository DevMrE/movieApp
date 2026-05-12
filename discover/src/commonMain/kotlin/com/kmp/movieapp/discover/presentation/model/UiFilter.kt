package com.kmp.movieapp.discover.presentation.model

data class UiFilter(
    val name: String,
    val filterType: UiFilterType,
    val isSeclected: Boolean = false
)
