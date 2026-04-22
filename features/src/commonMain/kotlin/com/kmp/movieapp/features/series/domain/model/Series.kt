package com.kmp.movieapp.features.series.domain.model

import com.kmp.movieapp.features.genre.domain.model.Genre

data class Series(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<Genre>? = null
)