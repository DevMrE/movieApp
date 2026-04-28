package com.kmp.movieapp.features.media_list.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.content_type.model.ContentDetailType

/**
 * Represents a single movie for the movie list screen
 */
@Stable
internal data class UiMediaCard(
    val id: String,
    val title: String,
    val genre: String,
    val posterPath: String?,
    val backdropPath: String?,
    val type: ContentDetailType
)