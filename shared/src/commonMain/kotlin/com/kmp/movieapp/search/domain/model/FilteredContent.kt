package com.kmp.movieapp.search.domain.model

import com.kmp.movieapp.core.util.mediaCategory.MediaCategory

data class FilteredContent(
    val id: Int,
    val title: String,
    val type: MediaCategory,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val filterGenres: List<FilterGenre> = emptyList(),
)
