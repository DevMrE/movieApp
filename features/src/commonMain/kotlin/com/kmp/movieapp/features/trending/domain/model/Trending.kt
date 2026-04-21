package com.kmp.movieapp.features.trending.domain.model

import com.kmp.movieapp.features.genre.domain.model.Genre

data class Trending(
    val id: Int,
    val title: String,
    val originTitle: String,
    val type: TrendingType,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<Genre>? = null,
)
