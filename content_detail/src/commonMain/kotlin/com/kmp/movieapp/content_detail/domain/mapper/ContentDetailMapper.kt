package com.kmp.movieapp.content_detail.domain.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.model.Genre
import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.features.series.domain.model.Series
import com.kmp.movieapp.movie.domain.model.Movie

fun Movie.toContentDetail() = ContentDetail(
    title = title,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = backdropPath,
    runtime = runtime,
    releaseDate = releaseDate,
    overview = overview,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

fun Series.toContentDetail() = ContentDetail(
    title = name,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = backdropPath,
    overview = overview,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

