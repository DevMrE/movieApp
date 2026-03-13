package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.presentation.model.UiMovie
import com.kmp.movieapp.movie.presentation.model.UiMovieList

internal fun Movie.toUiMovie() = UiMovie(
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

