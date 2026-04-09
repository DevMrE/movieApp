package com.kmp.series.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.series.data.model.response.SeriesDto
import com.kmp.series.domain.model.Genre
import com.kmp.series.domain.model.Series

fun SeriesDto.toSeries() = Series(
    id = id,
    name = name,
    adult = adult,
    overview = overview,
    firstAirDate = firstAirDate,
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    genres = genres?.map {
        Genre(it.name ?: "")
    }
)