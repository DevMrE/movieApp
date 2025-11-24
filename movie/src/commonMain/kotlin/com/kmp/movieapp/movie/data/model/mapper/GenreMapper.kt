package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.movie.data.model.response.GenreDto
import com.kmp.movieapp.movie.domain.model.MovieGenre

fun GenreDto.toMovieGenre(): MovieGenre = MovieGenre(
    id = id,
    name = name
)