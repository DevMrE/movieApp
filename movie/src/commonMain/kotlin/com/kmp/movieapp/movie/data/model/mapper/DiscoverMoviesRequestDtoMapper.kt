package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.movie.data.model.request.DiscoverMoviesRequestDto
import com.kmp.movieapp.movie.domain.model.Filter

fun Filter.toDiscoverMoviesDto() = DiscoverMoviesRequestDto(
    language = language,
    sortBy = sortBy.toSortByDto(),
    page = page,
    includeAdult = includeAdult,
    includeVideo = includeVideo,
    year = year
)