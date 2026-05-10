package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.movie.domain.model.MovieGenre
import com.kmp.movieapp.movie.data.model.response.GenreDto

/**
 * Mapper for transforming an [GenreDto] into
 * [MovieGenre].
 */
internal fun GenreDto.toMovieGenre(): MovieGenre =
    MovieGenre(
        id = id ?: 0,
        name = name ?: ""
    )