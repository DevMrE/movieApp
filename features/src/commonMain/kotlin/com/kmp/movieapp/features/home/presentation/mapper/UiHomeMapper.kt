package com.kmp.movieapp.features.home.presentation.mapper

import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.home.presentation.model.UiHomeList
import com.kmp.movieapp.features.media_list.presentation.model.UiMediaCard
import com.kmp.movieapp.features.home_popular_movies_title
import com.kmp.movieapp.features.home_trending_title
import com.kmp.movieapp.features.movie.data.domain.model.HomeCategory
import com.kmp.movieapp.features.movie.domain.model.Movie
import com.kmp.movieapp.features.trending.domain.model.Trending
import com.kmp.movieapp.features.trending.domain.model.TrendingType

internal fun Movie.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backDropPath,
    type = TrendingType.MOVIE
)

internal fun Trending.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = type
)

/**
 * Mapper to map the [List] of [Movie] into [UiHomeList].
 */
internal fun List<Movie>.toUiHomeMovieList(): UiHomeList {
    return UiHomeList(
        category = HomeCategory.POPULAR_MOVIES,
        title = Res.string.home_trending_title,
        movies = this.map { it.toUiMedia() }
    )
}

internal fun List<Trending>.toUiHomeTrendingList(): UiHomeList {
    return UiHomeList(
        category = HomeCategory.TRENDING,
        title = Res.string.home_popular_movies_title,
        movies = this.map { it.toUiMedia() }
    )
}


