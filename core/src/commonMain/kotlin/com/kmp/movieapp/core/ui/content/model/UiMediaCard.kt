package com.kmp.movieapp.core.ui.content.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.content_type.model.ContentDetailType

@Stable
data class UiMediaCard(
    val id: String,
    val title: String,
    val bigCard: Boolean = false,
    val posterPath: String?,
    val backdropPath: String?,
    val type: ContentDetailType
)
