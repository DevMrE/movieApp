package com.kmp.movieapp.features.home.data.model.mapper

import com.kmp.movieapp.features.home.data.model.request.discover.SortByRequestDto
import com.kmp.movieapp.features.home.domain.model.SortBy

internal fun SortBy.toSortByDto(): SortByRequestDto =
    SortByRequestDto.valueOf(this.name)

