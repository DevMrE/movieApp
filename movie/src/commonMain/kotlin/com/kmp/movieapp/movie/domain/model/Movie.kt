package com.kmp.movieapp.movie.domain.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

data class Movie(
    val id: Int,
    val movieInfo: MovieInfo,
    val movieImage: MovieImage,
    val voteAverage: Double,
    val type: MediaCategory = MediaCategory.MOVIE,
    val runtime: Int? = null,
    val releaseDate: String? = null,
    val genres: List<MovieGenre>? = emptyList(),
)
