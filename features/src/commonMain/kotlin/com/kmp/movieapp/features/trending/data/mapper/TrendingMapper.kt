package com.kmp.movieapp.features.trending.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.features.trending.data.model.api.request.TrendingRequestTypeDto
import com.kmp.movieapp.features.trending.data.model.api.response.TrendingResultDto
import com.kmp.movieapp.features.trending.domain.model.Trending
import com.kmp.movieapp.features.trending.domain.model.TrendingType

fun TrendingResultDto.toTrending() = Trending(
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