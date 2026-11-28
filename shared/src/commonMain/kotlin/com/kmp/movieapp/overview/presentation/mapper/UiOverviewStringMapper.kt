package com.kmp.movieapp.overview.presentation.mapper

import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.util.mediaCategory.MediaCategory
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.popular_movies_title
import com.kmp.movieapp.shared.popular_series_title
import org.jetbrains.compose.resources.stringResource

object UiOverviewStringMapper {

    @Composable
    fun toTitle(mediaCategory: MediaCategory) = stringResource(
        when (mediaCategory) {
            MediaCategory.SERIES -> Res.string.popular_series_title
            else -> Res.string.popular_movies_title
        }
    )
}