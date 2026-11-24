package com.kmp.movieapp.search.presentation.mapper

import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.domain.model.Search
import com.kmp.movieapp.search.domain.model.discover.DiscoverContent
import com.kmp.movieapp.search.domain.model.discover.DiscoverGenre
import com.kmp.movieapp.search.domain.model.discover.DiscoverType
import com.kmp.movieapp.search.domain.model.discover.Filter
import com.kmp.movieapp.search.presentation.model.UiSearch
import com.kmp.movieapp.search.presentation.model.filter.UiGenre
import com.kmp.movieapp.search.presentation.model.filter.UiGenreType

private fun DiscoverContent.toUiData() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = null,
    type = type
)

private fun DiscoverGenre.toUiGenre() = UiGenre(
    id = "$id",
    name = name
)

internal fun List<DiscoverContent>.toUiMediaCardList() =
    this.map { it.toUiData() }

private fun Genre.toUiGenre(): UiGenre = UiGenre(
    id = "$id",
    name = name
)

internal fun List<Genre>.toUiGenreList(): List<UiGenre> = map { genre ->
    genre.toUiGenre()
}

fun UiGenre.toDiscoverGenre(): DiscoverGenre = DiscoverGenre(
    id = id.toLong(),
    name = name,
    discoverType = when (uiGenreType) {
        UiGenreType.MOVIE -> DiscoverType.MOVIES
        UiGenreType.SERIES -> DiscoverType.SERIES
    }
)

fun UiSearch.mapToFilter(): Filter =
    Filter(
        genre = genreFilter?.genres?.filter { it.selected }?.map { it.toDiscoverGenre() }
    )

private fun Search.mapToUiMediaCard(): UiMediaCard = UiMediaCard(
    id = id,
    title = titleInfo.mainTitle,
    posterPath = media.posterUrl,
    backdropPath = media.backdropUrl,
    voteAverage = null,
    type = mediaCategory
)

fun List<Search>.mapToUiMediaCardList(): List<UiMediaCard> = map {
    it.mapToUiMediaCard()
}
