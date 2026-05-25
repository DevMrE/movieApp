package com.kmp.movieapp.browse.model

import com.kmp.movieapp.core.ui.content.model.UiMediaCard

data class UiDiscover(
    val page: Int = 1,
    val filter: List<UiFilter> = emptyList(),
    val contentList: List<UiMediaCard> = emptyList()
)