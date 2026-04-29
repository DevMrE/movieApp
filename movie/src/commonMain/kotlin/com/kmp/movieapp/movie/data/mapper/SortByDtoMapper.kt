package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.movie.data.domain.model.SortBy
import com.kmp.movieapp.movie.data.model.request.discover.SortByRequestDto

internal fun SortBy.toSortByDto(): SortByRequestDto =
    SortByRequestDto.valueOf(this.name)

