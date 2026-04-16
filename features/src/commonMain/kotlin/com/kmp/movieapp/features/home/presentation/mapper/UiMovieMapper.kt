package com.kmp.movieapp.features.home.presentation.mapper

import com.kmp.movieapp.features.home.domain.model.Movie
import com.kmp.movieapp.features.home.domain.model.MovieCategory
import com.kmp.movieapp.features.home.presentation.model.UiMovie
import com.kmp.movieapp.features.home.presentation.model.UiMovieList

internal fun Movie.toUiMovie() = UiMovie(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backDropPath,
)

internal fun List<Movie>.toUiMovieList(category: MovieCategory): UiMovieList {
    return UiMovieList(
        category = category,
        movies = this.map { it.toUiMovie() }
    )
}

