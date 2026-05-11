package com.kmp.movieapp.trending.presentation.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.content.model.UiSection
import com.kmp.movieapp.trending.domain.model.Trending
import com.kmp.movieapp.trending.domain.model.TrendingType

/**
 * Mapper to map the [Trending] into [UiMediaCard].
 */
internal fun Trending.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = when (type) {
        TrendingType.MOVIE -> MediaCategory.MOVIE
        TrendingType.SERIES -> MediaCategory.SERIES
        TrendingType.PEOPLE -> MediaCategory.ACTOR
        else -> MediaCategory.UNKNOWN
    }
)

/**
 * Mapper to map the [List] of [Trending] into [UiSection].
 */
fun List<Trending>.toUiHomeTrendingList(): UiSection {
    return UiSection(
        category = MediaCategory.UNKNOWN,
        title = null,
        items = map { it.toUiMedia() }
    )
}
