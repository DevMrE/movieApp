package com.kmp.movieapp.discover.presentation.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.discover.presentation.model.DiscoverSection
import com.kmp.movieapp.movie.domain.model.Movie
import com.kmp.movieapp.series.domain.model.Series
import com.kmp.movieapp.trending.domain.model.Trending

internal fun initDiscoverSection(
    trendingList: List<Trending>,
    popularMovies: List<Movie>,
    popularSeries: List<Series>
): Set<DiscoverSection> = buildSet {
    if (trendingList.isNotEmpty()) add(trendingList.toTrendingSection())
    if (popularMovies.isNotEmpty()) add(popularMovies.toPopularMovieSection())
    if (popularSeries.isNotEmpty()) add(popularSeries.toPopularSeriesSection())
}

private fun List<Trending>.toTrendingSection(): DiscoverSection.Trending =
    DiscoverSection.Trending(
        contentList = toUiMediaCard()
    )

private fun List<Movie>.toPopularMovieSection(): DiscoverSection.PopularMovies =
    DiscoverSection.PopularMovies(
        contentList = map { it.toUiMedia() }
    )

private fun List<Series>.toPopularSeriesSection(): DiscoverSection.PopularSeries =
    DiscoverSection.PopularSeries(
        contentList = map { it.toUiMedia() }
    )

private fun List<Trending>.toUiMediaCard(): List<UiMediaCard> = map { trending ->
    UiMediaCard(
        id = "${trending.id}",
        title = trending.title,
        posterPath = trending.posterPath,
        backdropPath = trending.backdropPath,
        type = MediaCategory.UNKNOWN
    )
}

private fun Series.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = name,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = MediaCategory.MOVIE
)

private fun Movie.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = movieInfo.title,
    posterPath = movieImage.posterPath,
    backdropPath = movieImage.backdropPath,
    type = MediaCategory.MOVIE
)