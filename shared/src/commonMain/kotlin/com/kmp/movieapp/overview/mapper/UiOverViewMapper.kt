package com.kmp.movieapp.overview.mapper

import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.ui.content.model.UiSection
import com.kmp.movieapp.overview.domain.model.OverViewMedia

private fun OverViewMedia.toUiMediaCard() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    type = type
)

/**
 * Mapper to mapOnSuccess the [List] of [OverViewMedia] into [UiSection].
 */
internal fun List<OverViewMedia>.toUiMediaCardList(): List<UiMediaCard> =
    this.map { it.toUiMediaCard() }
