package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.movie.data.model.response.movie.MovieDto
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieGenre

/**
 * Mapper for transforming an [MovieForCategoryDto] into
 * [Movie]
 */
internal fun MovieForCategoryDto.toMovie() = Movie(
    id = id ?: 0,
    title = title ?: "",
    overview = overview,
    releaseDate = releaseDate,
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backDropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
    genres = genreIds?.map {
        MovieGenre(
            id = it,
            name = ""
        )
    },
    runtime = null
)

/**
 * Mapper for transforming an [MovieDto] into
 * [Movie]
 */
internal fun MovieDto.toMovie() = Movie(
    id = id ?: 0,
    title = title ?: "",
    overview = overview,
    runtime = runtime,
    releaseDate = releaseDate,
    genres = genres?.map {
        MovieGenre(it.id ?: 0, name = "")
    },
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backDropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)


