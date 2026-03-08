package com.kmp.movieapp.content_detail.presentation.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi

fun ContentDetail.toUiData(): ContentDetailUi = ContentDetailUi(
    title = title,
    description = overview ?: "",
    posterPath = posterPath ?: "",
    videoThumbnail = backdropPath ?: ""
)