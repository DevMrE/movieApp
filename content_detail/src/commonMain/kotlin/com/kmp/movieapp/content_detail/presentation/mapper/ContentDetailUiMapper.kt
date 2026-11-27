package com.kmp.movieapp.content_detail.presentation.mapper

import com.kmp.movieapp.content_detail.domain.model.ContentDetail
import com.kmp.movieapp.content_detail.presentation.model.UiContentDetail
import com.kmp.movieapp.content_detail.presentation.model.UiContentDetailState
import com.kmp.movieapp.core.util.boolean.isFalse

internal fun ContentDetail?.toUiData(): UiContentDetailState {
    return if (this == null) UiContentDetailState()
    else UiContentDetailState(
        isLoading = info.title.localized.isNotEmpty()
                && info.synopsis.isNullOrEmpty().isFalse
                && images != null
                && metadata != null,
        uiContentDetail = UiContentDetail(
            title = info.title.localized,
            releaseDate = metadata?.releaseDate,
            runtime = metadata?.runtime,
            genres = metadata?.genres,
            description = "${info.synopsis}",
            posterPath = "${images?.posterPath}",
            videoThumbnail = "${images?.backdropPath}",
        )
    )
}