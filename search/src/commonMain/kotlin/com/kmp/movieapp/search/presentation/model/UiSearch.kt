package com.kmp.movieapp.search.presentation.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

internal data class UiSearch(
    val mediaCategory: MediaCategory,
    val id: String,
    val title: String,
    val posterPath: String
)
