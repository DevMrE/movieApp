package com.kmp.movieapp.components.mediacard

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory
import com.kmp.movieapp.model.UiMediaCard

class MovieCardPreviewData : PreviewParameterProvider<List<UiMediaCard>> {
    override val values: Sequence<List<UiMediaCard>>
        get() = sequenceOf(
            (0..100).map {
                UiMediaCard(
                    id = "",
                    title = "Media $it",
                    posterPath = null,
                    backdropPath = null,
                    voteAverage = 8.8,
                    type = MediaCategory.MOVIE
                )
            }
        )
}

class SeriesCardPreviewData : PreviewParameterProvider<List<UiMediaCard>> {
    override val values: Sequence<List<UiMediaCard>>
        get() = sequenceOf(
            (0..100).map {
                UiMediaCard(
                    id = "",
                    title = "Media $it",
                    posterPath = null,
                    backdropPath = null,
                    voteAverage = 8.8,
                    type = MediaCategory.SERIES
                )
            }
        )
}
