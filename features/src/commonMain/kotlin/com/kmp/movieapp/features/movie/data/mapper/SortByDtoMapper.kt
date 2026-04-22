package com.kmp.movieapp.features.movie.data.mapper

import com.kmp.movieapp.features.movie.data.model.request.discover.SortByRequestDto
import com.kmp.movieapp.features.movie.data.domain.model.SortBy

internal fun SortBy.toSortByDto(): SortByRequestDto =
    SortByRequestDto.valueOf(this.name)

