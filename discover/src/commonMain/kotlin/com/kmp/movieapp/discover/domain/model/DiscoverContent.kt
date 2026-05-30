package com.kmp.movieapp.discover.domain.model

data class DiscoverContent(
    val id: Int,
    val title: String,
    val type: DiscoverType,
    val posterPath: String,
    val backdropPath: String,
    val discoverGenres: List<DiscoverGenre> = emptyList(),
)
