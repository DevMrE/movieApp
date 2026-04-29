package com.kmp.movieapp.features.media_list.presentation.mapper

import com.kmp.movieapp.features.media_list.presentation.model.UiMediaCard
import com.kmp.movieapp.movie.domain.model.Movie

/**
 * Mapper to map the [List] of [com.kmp.movieapp.movie.domain.model.Movie] into [UiMediaCard].
 */
internal fun List<Movie>.toMediaList(): List<UiMediaCard> {
    return map { movie ->
        UiMediaCard(
            id = movie.id.toString(),
            title = movie.title,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            type = movie.type,
            genre = movie.genres?.joinToString(separator = ", ") { it.name } ?: ""
        )
    }
}