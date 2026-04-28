package com.kmp.movieapp.features.movie.domain.model

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.content_type.model.Media
import com.kmp.movieapp.features.movie.data.domain.model.MovieGenre

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
) : Media(contentDetailType = type)