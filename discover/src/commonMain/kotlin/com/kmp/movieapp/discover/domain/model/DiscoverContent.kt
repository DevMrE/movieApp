package com.kmp.movieapp.discover.domain.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

data class DiscoverContent(
    val id: Int,
    val title: String,
    val type: MediaCategory,
    val posterPath: String,
    val backdropPath: String,
    val discoverGenres: List<DiscoverGenre> = emptyList(),
)
