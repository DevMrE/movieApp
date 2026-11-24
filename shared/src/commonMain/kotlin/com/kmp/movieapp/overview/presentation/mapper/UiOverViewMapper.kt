package com.kmp.movieapp.overview.presentation.mapper

import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.overview.domain.model.OverViewMedia
import com.kmp.movieapp.overview.presentation.OverviewUiState

private fun OverViewMedia.toUiMediaCard() = UiMediaCard(
    id = id.toString(),
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = null,
    type = type
)

private fun List<OverViewMedia>.toUiMediaCardList(): List<UiMediaCard> =
    this.map { it.toUiMediaCard() }
        .distinctBy { it.id }

internal fun List<OverViewMedia>.toUiState(): OverviewUiState =
    OverviewUiState(
        contentList = toUiMediaCardList()
    )

