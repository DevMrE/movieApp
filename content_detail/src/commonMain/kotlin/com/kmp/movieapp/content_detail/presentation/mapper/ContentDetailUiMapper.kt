package com.kmp.movieapp.content_detail.presentation.mapper

import androidx.compose.ui.text.buildAnnotatedString
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.presentation.model.ContentDetailUi
import com.kmp.movieapp.core.util.boolean.isFalse

fun ContentDetail.toUiData(): ContentDetailUi = ContentDetailUi(
    isLoading = title.isNotEmpty() && posterPath.isNullOrEmpty().isFalse && backdropPath.isNullOrEmpty().isFalse && overview.isNullOrEmpty().isFalse,
    title = title,
    mediaInfo = buildAnnotatedString {
        append(releaseDate)
        append(" \u2022 ")
        append(runtime.toString())
    },
    description = overview ?: "",
    posterPath = posterPath ?: "",
    videoThumbnail = backdropPath ?: ""
)