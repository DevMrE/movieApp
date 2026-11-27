package com.kmp.movieapp.series.data.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.series.data.model.response.series_detail.SeriesResponse
import com.kmp.movieapp.series.domain.model.Series

fun List<SeriesResponse>.toSeriesList() = map {
    it.toSeries()
}

fun SeriesResponse.toSeries() = Series(
    id = id ?: 0,
    name = "$name",
    overview = "$overview",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    voteAverage = voteAverage ?: 0.0
)