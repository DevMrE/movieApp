package com.kmp.movieapp.trending.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.trending.data.model.request.TrendingRequestTypeDto
import com.kmp.movieapp.trending.data.model.response.TrendingResultDto
import com.kmp.movieapp.trending.domain.model.TrendingType

fun TrendingResultDto.toTrending() =
    _root_ide_package_.com.kmp.movieapp.trending.domain.model.Trending(
        title = name ?: title ?: "",
        originTitle = originalTitle ?: "",
        id = id ?: 0,
        type = when (mediaType) {
            TrendingRequestTypeDto.MOVIE -> TrendingType.MOVIE
            TrendingRequestTypeDto.PEOPLE -> TrendingType.PEOPLE
            TrendingRequestTypeDto.SERIES -> TrendingType.SERIES
            else -> TrendingType.ALL
        },
        posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
        backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    )