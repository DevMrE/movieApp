package com.kmp.movieapp.content_detail.domain.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.domain.model.Genre
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.series.domain.model.Series

fun Movie.toContentDetail() = ContentDetail(
    title = movieInfo.title,
    posterPath =  movieImage.posterPath,
    backdropPath = movieImage.backdropPath,
    runtime = runtime,
    releaseDate = releaseDate,
    overview = movieInfo.overview,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

fun Series.toContentDetail() = ContentDetail(
    title = name,
    posterPath = posterPath,
    backdropPath = backdropPath,
    overview = overview,
    genres = genres?.map {
        Genre(name = it.name)
    }
)

