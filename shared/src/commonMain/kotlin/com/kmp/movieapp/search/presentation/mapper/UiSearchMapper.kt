package com.kmp.movieapp.search.presentation.mapper

import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.domain.model.FilterGenre
import com.kmp.movieapp.search.domain.model.FilterMediaType
import com.kmp.movieapp.search.domain.model.FilterType
import com.kmp.movieapp.search.domain.model.FilteredContent
import com.kmp.movieapp.search.presentation.model.UiSearch
import com.kmp.movieapp.search.presentation.model.filter.UiGenre
import com.kmp.movieapp.search.presentation.model.filter.UiGenreType

private fun FilteredContent.toUiData() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = null,
    type = type
)

private fun FilterGenre.toUiGenre() = UiGenre(
    id = "$id",
    name = name
)

internal fun List<FilteredContent>.toUiMediaCardList() =
    this.map { it.toUiData() }

private fun Genre.toUiGenre(): UiGenre = UiGenre(
    id = "$id",
    name = name
)

internal fun List<Genre>.toUiGenreList(): List<UiGenre> = map { genre ->
    genre.toUiGenre()
}

fun UiGenre.toDiscoverGenre(): FilterGenre = FilterGenre(
    id = id.toLong(),
    name = name,
    filterMediaType = when (uiGenreType) {
        UiGenreType.MOVIE -> FilterMediaType.MOVIES
        UiGenreType.SERIES -> FilterMediaType.SERIES
    }
)

fun UiSearch.mapToFilter(): FilterType =
    FilterType(
        genre = genreFilter?.genres?.filter { it.selected }?.map { it.toDiscoverGenre() }
    )

private fun FilteredContent.mapToUiMediaCard(): UiMediaCard = UiMediaCard(
    id = "$id",
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    type = type
)


fun List<FilteredContent>.mapToUiMediaCardList(): List<UiMediaCard> = map {
    it.mapToUiMediaCard()
}
