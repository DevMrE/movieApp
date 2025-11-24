package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.presentation.model.UiMovie

fun Movie.toUiMovie() = UiMovie(
    id = id,
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
)