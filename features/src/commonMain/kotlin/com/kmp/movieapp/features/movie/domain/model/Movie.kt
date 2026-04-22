package com.kmp.movieapp.features.movie.domain.model

import com.kmp.movieapp.features.movie.data.domain.model.MovieGenre

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String,
    val backdropPath: String,
    val runtime: Int?,
    val releaseDate: String?,
    val genres: List<MovieGenre>? = emptyList(),
)