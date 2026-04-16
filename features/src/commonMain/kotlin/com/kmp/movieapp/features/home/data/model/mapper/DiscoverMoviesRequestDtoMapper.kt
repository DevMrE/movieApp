package com.kmp.movieapp.features.home.data.model.mapper

import com.kmp.movieapp.features.home.data.model.request.discover.DiscoverMoviesRequestDto
import com.kmp.movieapp.features.home.domain.model.Filter

internal fun Filter.toDiscoverMoviesDto() = DiscoverMoviesRequestDto(
    language = language,
    sortBy = sortBy.toSortByDto(),
    page = page,
    includeAdult = includeAdult,
    includeVideo = includeVideo,
    year = year
)