package com.kmp.movieapp.discover.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.discover.data.model.response.DiscoverMoviesDto
import com.kmp.movieapp.discover.data.model.response.DiscoverSeriesDto
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.DiscoverType

internal fun DiscoverMoviesDto.toDiscoverMovies() = Discover(
    title = title ?: "",
    id = id ?: 0,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    type = DiscoverType.MOVIES
)

internal fun DiscoverSeriesDto.toDiscoverSeries() = Discover(
    title = name ?: "",
    id = id ?: 0,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    type = DiscoverType.SERIES
)

