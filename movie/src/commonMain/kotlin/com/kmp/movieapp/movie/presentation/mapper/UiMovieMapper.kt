package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.movie.domain.model.Movie

fun Movie.toUiMovieCard() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = ContentDetailType.MOVIE
)