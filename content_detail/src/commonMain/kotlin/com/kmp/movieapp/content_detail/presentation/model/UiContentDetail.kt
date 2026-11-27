package com.kmp.movieapp.content_detail.presentation.model

import com.kmp.movieapp.content_detail.domain.model.ContentDetailGenre

internal data class UiContentDetail(
    val title: String,
    val description: String,
    val posterPath: String,
    val videoThumbnail: String,
    val releaseDate: String?,
    val runtime: Int?,
    val genres: List<ContentDetailGenre>?,
)
