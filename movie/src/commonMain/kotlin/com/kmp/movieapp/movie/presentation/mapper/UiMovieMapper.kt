package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.movie.Res
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.popular
import com.kmp.movieapp.movie.presentation.model.UiMovie
import com.kmp.movieapp.movie.presentation.model.UiMovieList
import com.kmp.movieapp.movie.top_rated

fun Movie.toUiMovie() = UiMovie(
    id = id,
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
)

fun List<Movie>.toUiMovieList(category: MovieCategory): UiMovieList = UiMovieList(
    titleRes = when (category) {
        MovieCategory.POPULAR -> Res.string.popular
        MovieCategory.TOP_RATED -> Res.string.top_rated
        else -> null
    },
    movies = this.map { it.toUiMovie() }
)

