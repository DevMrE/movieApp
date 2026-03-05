package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.core.data.url.UrlHelper
import com.kmp.movieapp.core.domain.repository.Repository
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.domain.model.Movie

/**
 * Mapper for transforming an [MovieDto] into
 * [Movie]
 */
context(repository: Repository)
fun MovieDto.toMovie() = Movie(
    id = id ?: 0,
    title = title ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backDropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)
