package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.movie.Res
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.popular
import com.kmp.movieapp.movie.presentation.model.UiMovie
import com.kmp.movieapp.movie.presentation.model.UiMovieList
import com.kmp.movieapp.movie.top_rated
import org.jetbrains.compose.resources.getString

fun Movie.toUiMovie() = UiMovie(
    id = id,
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
)

suspend fun List<Movie>.toUiMovieList(category: MovieCategory): UiMovieList = UiMovieList(
    title = when (category) {
        MovieCategory.POPULAR -> getString(Res.string.popular)
        MovieCategory.TOP_RATED -> getString(Res.string.top_rated)
        else -> null
    },
    movies = this.map { it.toUiMovie() }
)

