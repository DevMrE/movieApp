package com.kmp.movieapp.movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backDropPath: String,
    val genres: List<MovieGenre>? = emptyList(),
)
