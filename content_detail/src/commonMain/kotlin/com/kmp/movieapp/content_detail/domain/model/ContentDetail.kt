package com.kmp.movieapp.content_detail.domain.model

data class ContentDetail(
    val title: String,
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val genres: List<Genre>? = emptyList(),
    val imdbId: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val video: Video? = null
)
