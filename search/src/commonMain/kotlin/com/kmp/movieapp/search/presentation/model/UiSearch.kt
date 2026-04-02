package com.kmp.movieapp.search.presentation.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType

internal data class UiSearch(
    val contentDetailType: ContentDetailType,
    val id: String,
    val title: String,
    val posterPath: String
)
