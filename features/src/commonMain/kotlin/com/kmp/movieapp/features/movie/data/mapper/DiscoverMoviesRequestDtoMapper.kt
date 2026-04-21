package com.kmp.movieapp.features.movie.data.mapper

import com.kmp.movieapp.features.movie.data.model.request.discover.DiscoverMoviesRequestDto
import com.kmp.movieapp.features.home.domain.model.Filter

internal fun Filter.toDiscoverMoviesDto() = DiscoverMoviesRequestDto(
    sortBy = sortBy.toSortByDto(),
    page = page,
    includeAdult = includeAdult,
    includeVideo = includeVideo,
    year = year
)