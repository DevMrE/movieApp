package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.movie.data.domain.model.Filter
import com.kmp.movieapp.movie.data.model.request.discover.DiscoverMoviesRequestDto

internal fun Filter.toDiscoverMoviesDto() =
    DiscoverMoviesRequestDto(
        sortBy = sortBy.toSortByDto(),
        page = page,
        includeAdult = includeAdult,
        includeVideo = includeVideo,
        year = year
    )