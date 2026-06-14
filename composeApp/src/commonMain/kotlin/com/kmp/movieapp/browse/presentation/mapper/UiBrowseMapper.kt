package com.kmp.movieapp.browse.presentation.mapper

import com.kmp.movieapp.browse.presentation.model.UiBrowse
import com.kmp.movieapp.browse.presentation.model.filter.UiGenre
import com.kmp.movieapp.browse.presentation.model.filter.UiGenreType
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.discover.domain.model.DiscoverContent
import com.kmp.movieapp.discover.domain.model.DiscoverGenre
import com.kmp.movieapp.discover.domain.model.DiscoverType
import com.kmp.movieapp.discover.domain.model.Filter
import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.search.domain.model.Search

private fun DiscoverContent.toUiData() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    bigCard = false,
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

fun UiBrowse.mapToFilter(): Filter =
    Filter(
        genre = genreFilter?.genres?.filter { it.selected }?.map { it.toDiscoverGenre() }
    )

private fun Search.mapToUiMediaCard(): UiMediaCard = UiMediaCard(
    id = id,
    title = titleInfo.mainTitle,
    posterPath = media.posterUrl,
    backdropPath = media.backdropUrl,
    type = mediaCategory
)

fun List<Search>.mapToUiMediaCardList(): List<UiMediaCard> = map {
    it.mapToUiMediaCard()
}
