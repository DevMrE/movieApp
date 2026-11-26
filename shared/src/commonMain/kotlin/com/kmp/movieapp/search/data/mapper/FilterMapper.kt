package com.kmp.movieapp.search.data.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.search.domain.model.FilteredContent
import com.kmp.movieapp.series.domain.model.Series

internal fun Movie.toFilteredMovies() = FilteredContent(
    title = movieInfo.title,
    id = id,
    posterPath = movieImage.posterPath,
    backdropPath = movieImage.backdropPath,
    voteAverage = voteAverage ?: 0.0,
    type = MediaCategory.MOVIE,
)

internal fun Series.toFilteredSeries() = FilteredContent(
    title = name,
    id = id,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    type = MediaCategory.SERIES,
)
