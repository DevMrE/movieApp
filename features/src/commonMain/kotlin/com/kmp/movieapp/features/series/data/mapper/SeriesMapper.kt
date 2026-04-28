package com.kmp.movieapp.features.series.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.features.series.data.model.api.response.series_detail.SeriesDetailDto
import com.kmp.movieapp.features.series.data.model.api.response.series_list.SeriesResultDto
import com.kmp.movieapp.features.series.domain.model.Series

fun SeriesResultDto.toSeries() = Series(
    id = id ?: 0,
    name = name ?: "",
    overview = overview ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
)

fun SeriesDetailDto.toSeries() = Series(
    id = id ?: 0,
    name = name ?: "",
    overview = overview ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
)