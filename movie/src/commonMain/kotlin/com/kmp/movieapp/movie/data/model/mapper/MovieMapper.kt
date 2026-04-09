package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.core.network.url.UrlHelper
import com.kmp.movieapp.movie.data.model.response.movie_for_category.MovieForCategoryDto
import com.kmp.movieapp.movie.domain.model.Movie

/**
 * Mapper for transforming an [MovieForCategoryDto] into
 * [Movie]
 */
internal fun MovieForCategoryDto.toMovie() = Movie(
    id = id ?: 0,
    title = title ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
    backDropPath = "${UrlHelper.IMAGE_BASE_URL}$backdropPath",
)
