package com.kmp.movieapp.media_list.presentation.mapper

import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.movie.domain.model.Movie

/**
 * Mapper to map the [List] of [Movie] into [UiMediaCard].
 */
internal fun List<Movie>.toMediaList(): List<UiMediaCard> {
    return map { movie ->
        UiMediaCard(
            id = movie.id.toString(),
            title = movie.movieInfo.title,
            posterPath = movie.movieImage.posterPath,
            backdropPath = movie.movieImage.backdropPath,
            type = movie.type,
        )
    }
}