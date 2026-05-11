package com.kmp.movieapp.overview_list.domain.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

data class OverViewMedia(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val type: MediaCategory
)
