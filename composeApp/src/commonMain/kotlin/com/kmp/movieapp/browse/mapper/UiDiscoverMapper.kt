package com.kmp.movieapp.browse.mapper

import com.kmp.movieapp.browse.model.UiDiscover
import com.kmp.movieapp.browse.model.UiFilter
import com.kmp.movieapp.browse.model.UiFilterType
import com.kmp.movieapp.browse.model.UiGenre
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.model.DiscoverType

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

internal fun List<Discover>.toUiMediaCardList() =
    this.map { it.toUiData() }

internal fun List<Discover>.toUiDiscoverList(): UiDiscover = UiDiscover(
    filter = listOf(
        UiFilter(
            name = "Genre",
            filterType = UiFilterType.ByGenre(
                genres = listOf(
                    UiGenre(
                        id = "1",
                        name = "Thriller"
                    )
                )
            )
        )
    ),
    contentList = this.toUiMediaCardList()
)


