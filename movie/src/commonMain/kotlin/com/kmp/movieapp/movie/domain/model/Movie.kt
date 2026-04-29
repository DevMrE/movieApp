package com.kmp.movieapp.movie.domain.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.movie.data.domain.model.MovieGenre

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String,
    val backdropPath: String,
    val runtime: Int?,
    val releaseDate: String?,
    val genres: List<MovieGenre>? = emptyList(),
    val type: ContentDetailType = ContentDetailType.SERIES
)