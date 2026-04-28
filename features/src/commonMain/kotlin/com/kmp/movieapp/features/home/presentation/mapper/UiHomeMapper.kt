package com.kmp.movieapp.features.home.presentation.mapper

import com.kmp.movieapp.core.content_type.model.ContentDetailType
import com.kmp.movieapp.features.Res
import com.kmp.movieapp.features.home.presentation.model.HomeCategory
import com.kmp.movieapp.features.home.presentation.model.UiHomeList
import com.kmp.movieapp.features.home_popular_movies_title
import com.kmp.movieapp.features.home_popular_series_title
import com.kmp.movieapp.features.home_trending_title
import com.kmp.movieapp.features.media_list.presentation.model.UiMediaCard
import com.kmp.movieapp.features.movie.domain.model.Movie
import com.kmp.movieapp.features.series.domain.model.Series
import com.kmp.movieapp.features.trending.domain.model.Trending
import com.kmp.movieapp.features.trending.domain.model.TrendingType

/**
 * Mapper to map the [Movie] into [UiMediaCard].
 */
internal fun Movie.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = getHomeCategory(TrendingType.MOVIE)
)

/**
 * Mapper to map the [Series] into [UiMediaCard].
 */
internal fun Series.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = name,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = getHomeCategory(TrendingType.SERIES)
)

/**
 * Mapper to map the [Trending] into [UiMediaCard].
 */
internal fun Trending.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = getHomeCategory(type)
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

/**
 * Mapper to map the [List] of [Series] into [UiHomeList].
 */
internal fun List<Series>.toUiHomeSeriesList(): UiHomeList {
    return UiHomeList(
        category = HomeCategory.POPULAR_SERIES,
        title = Res.string.home_popular_series_title,
        movies = this.map { it.toUiMedia() }
    )
}

/**
 * Mapper to map the [List] of [Trending] into [UiHomeList].
 */
internal fun List<Trending>.toUiHomeTrendingList(): UiHomeList {
    return UiHomeList(
        category = HomeCategory.TRENDING,
        title = Res.string.home_popular_movies_title,
        movies = this.map { it.toUiMedia() }
    )
}

private fun getHomeCategory(trendingType: TrendingType): ContentDetailType {
    return when (trendingType) {
        TrendingType.SERIES -> ContentDetailType.SERIES
        TrendingType.PEOPLE -> ContentDetailType.PERSON
        else -> ContentDetailType.MOVIE
    }
}