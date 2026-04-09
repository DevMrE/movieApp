package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.movie.data.model.request.discover.SortByRequestDto
import com.kmp.movieapp.movie.domain.model.SortBy

internal fun SortBy.toSortByDto(): SortByRequestDto =
    SortByRequestDto.valueOf(this.name)

