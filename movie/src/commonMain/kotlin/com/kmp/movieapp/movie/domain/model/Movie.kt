package com.kmp.movieapp.movie.domain.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory

data class Movie(
    val id: Int,
    val info: MovieInfo,
    val image: MovieImage,
    val metaData: MovieMetaData,
    val voteAverage: Double,
    val type: MediaCategory = MediaCategory.MOVIE,
    val genres: List<MovieGenre>? = emptyList(),
)
