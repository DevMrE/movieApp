package com.kmp.movieapp.browse.data.mapper

import com.kmp.movieapp.browse.domain.model.discover.DiscoverContent
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.series.domain.model.Series

internal fun Movie.toDiscoverMovies() = DiscoverContent(
    title = movieInfo.title,
    id = id,
    posterPath = movieImage.posterPath,
    backdropPath = movieImage.backdropPath,
    type = MediaCategory.MOVIE,
)

internal fun Series.toDiscoverSeries() = DiscoverContent(
    title = name,
    id = id,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = MediaCategory.SERIES,
)
