package com.kmp.movieapp.series.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.series.data.model.response.series_detail.SeriesDetailDto
import com.kmp.movieapp.series.data.model.response.series_list.SeriesResultDto
import com.kmp.movieapp.series.domain.model.Series

fun SeriesResultDto.toSeries() =
    Series(
        id = id ?: 0,
        name = "$name",
        overview = "$overview",
        posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
    )

fun SeriesDetailDto.toSeries() =
    Series(
        id = id ?: 0,
        name = "$name",
        overview = "$overview",
        posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath"
    )