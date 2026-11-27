package com.kmp.movieapp.content_detail.domain.model

internal data class ContentInfo(
    val title: ContentTitle,
    val synopsis: String? = null,
    val tagline: String? = null,
)
