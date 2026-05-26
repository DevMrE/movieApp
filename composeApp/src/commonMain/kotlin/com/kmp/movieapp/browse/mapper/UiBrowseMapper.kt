package com.kmp.movieapp.browse.mapper

import com.kmp.movieapp.browse.model.UiBrowse
import com.kmp.movieapp.browse.model.filter.UiGenre
import com.kmp.movieapp.browse.model.filter.UiGenreType
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.DiscoverGenre
import com.kmp.movieapp.discover.domain.model.DiscoverType
import com.kmp.movieapp.genre.domain.model.Genre

private fun Discover.toUiData() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    bigCard = false,
    type = when (type) {
        DiscoverType.SERIES -> MediaCategory.SERIES
        else -> MediaCategory.MOVIE
    }
)

private fun DiscoverGenre.toUiGenre() = UiGenre(
    id = "$id",
    name = name
)


internal fun List<Discover>.toUiMediaCardList() =
    this.map { it.toUiData() }

internal fun List<Discover>.toUiBrowseList(): UiBrowse = UiBrowse(
    contentList = toUiMediaCardList()
)


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