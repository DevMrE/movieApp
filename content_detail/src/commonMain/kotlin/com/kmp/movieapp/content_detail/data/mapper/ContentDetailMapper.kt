package com.kmp.movieapp.content_detail.data.mapper

import com.kmp.movieapp.content_detail.data.model.api.response.ContentDetailDto
import com.kmp.movieapp.content_detail.domain.model.ContentDetail

fun ContentDetailDto.toContentDetail() = ContentDetail(
    title = title ?: ""
)