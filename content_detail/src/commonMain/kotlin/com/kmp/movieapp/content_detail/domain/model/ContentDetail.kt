package com.kmp.movieapp.content_detail.domain.model

internal data class ContentDetail(
    val info: ContentInfo,
    val images: ContentImages? = null,
    val metadata: ContentMetaData? = null,
    val statistics: ContentStatistics = ContentStatistics(),
    val video: Video? = null,
)
