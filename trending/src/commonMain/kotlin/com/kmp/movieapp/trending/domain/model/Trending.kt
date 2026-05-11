package com.kmp.movieapp.trending.domain.model

data class Trending(
    val id: Int,
    val title: String,
    val originTitle: String,
    val type: TrendingType,
    val posterPath: String,
    val backdropPath: String,
    val genres: List<TrendingGenre>? = null,
)
