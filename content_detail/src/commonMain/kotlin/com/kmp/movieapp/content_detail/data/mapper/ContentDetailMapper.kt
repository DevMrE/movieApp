package com.kmp.movieapp.content_detail.data.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.model.Genre
import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.series.data.model.response.SeriesDto

fun MovieDto.toContentDetail() = ContentDetail(
    title = title ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backdropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    runtime = runtime,
    releaseDate = releaseDate,
    overview = overview,
    genres = genres?.map {
        Genre(name = it?.name ?: "")
    }
)

fun SeriesDto.toContentDetail() = ContentDetail(
    title = name ?: "",
    adult = adult ?: false,
    overview = overview,
    releaseDate = firstAirDate
)