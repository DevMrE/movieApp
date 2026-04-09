package com.kmp.movieapp.content_detail.data.mapper

import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.core.network.url.UrlHelper

fun ContentDetailDto.toContentDetail() = ContentDetail(
    title = title ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    runtime = runtime,
    releaseDate = releaseDate
)