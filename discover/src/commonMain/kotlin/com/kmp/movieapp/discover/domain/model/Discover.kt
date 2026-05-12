package com.kmp.movieapp.discover.domain.model

data class Discover(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val type: DiscoverType
)
