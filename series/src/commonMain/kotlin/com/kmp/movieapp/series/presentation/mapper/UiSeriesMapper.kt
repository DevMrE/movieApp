package com.kmp.movieapp.series.presentation.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.core.ui.content.model.UiSection
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.series.domain.model.Series

/**
 * Mapper to map the [Series] into [UiMediaCard].
 */
internal fun Series.toUiMedia() = UiMediaCard(
    id = id.toString(),
    title = name,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = MediaCategory.SERIES
)

/**
 * Mapper to map the [List] of [Series] into [UiSection].
 */
fun List<Series>.toUiHomeSeriesList(): UiSection {
    return UiSection(
        category = MediaCategory.SERIES,
        title = null,
        items = map { it.toUiMedia() }
    )
}
