package com.kmp.movieapp.movie.presentation.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.content.model.UiSection
import com.kmp.movieapp.movie.domain.model.Movie

/**
 * Mapper to map the [Movie] into [UiMediaCard].
 */
internal fun Movie.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = movieInfo.title,
    bigCard = true,
    posterPath = movieImage.posterPath,
    backdropPath = movieImage.backdropPath,
    type = MediaCategory.MOVIE
)

/**
 * Mapper to map the [List] of [Movie] into [UiSection].
 */
fun List<Movie>.toUiHomeMovieList(): UiSection {
    return UiSection(
        category = MediaCategory.SERIES,
        title = null,
        items = this.map { it.toUiMedia() }
    )
}