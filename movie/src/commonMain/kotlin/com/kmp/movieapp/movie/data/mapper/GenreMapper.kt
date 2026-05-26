package com.kmp.movieapp.movie.data.mapper

import com.kmp.movieapp.movie.data.model.response.GenreDto
import com.kmp.movieapp.movie.domain.model.MovieGenre

/**
 * Mapper for transforming an [GenreDto] into
 * [MovieGenre].
 */
internal fun List<GenreDto>.toMovieGenres(): List<MovieGenre> = this.map { dto ->
    MovieGenre(
        name = "${dto.name}"
    )
}