package com.kmp.movieapp.features.home.data.model.mapper

import com.kmp.movieapp.features.home.data.model.response.GenreDto
import com.kmp.movieapp.features.home.domain.model.MovieGenre

/**
 * Mapper for transforming an [GenreDto] into
 * [MovieGenre].
 */
internal fun GenreDto.toMovieGenre(): MovieGenre = MovieGenre(
    id = id ?: 0,
    name = name ?: ""
)