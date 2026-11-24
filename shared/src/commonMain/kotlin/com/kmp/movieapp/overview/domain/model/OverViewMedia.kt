package com.kmp.movieapp.overview.domain.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

internal data class OverViewMedia(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val type: MediaCategory
)
