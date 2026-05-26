package com.kmp.movieapp.browse.model

import com.kmp.movieapp.core.ui.content.model.UiMediaCard

data class UiBrowse(
    val page: Int = 1,
    val contentList: List<UiMediaCard> = emptyList(),
)