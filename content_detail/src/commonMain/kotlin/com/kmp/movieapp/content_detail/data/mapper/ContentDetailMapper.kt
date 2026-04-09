package com.kmp.movieapp.content_detail.data.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.model.Genre
import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.series.domain.model.Series

fun Movie.toContentDetail() = ContentDetail(
    title = title,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = backDropPath,
    runtime = runtime,
    releaseDate = releaseDate,
    overview = overview,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

fun Series.toContentDetail() = ContentDetail(
    title = name ?: "",
    adult = adult ?: false,
    overview = overview,
    releaseDate = firstAirDate,
    posterPath = posterPath,
    backdropPath = backdropPath,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

