package com.kmp.movieapp.movie.domain.model

data class MovieMetaData(
    val adult: Boolean = false,
    val runtime: Int? = null,
    val releaseDate: String? = null,
)