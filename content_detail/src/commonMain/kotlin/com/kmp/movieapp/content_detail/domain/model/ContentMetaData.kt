package com.kmp.movieapp.content_detail.domain.model

internal data class ContentMetaData(
    val adult: Boolean = false,
    val runtime: Int? = null,
    val releaseDate: String? = null,
    val genres: List<ContentDetailGenre> = emptyList(),
)