package com.kmp.movieapp.overview.presentation.model

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.model.UiMediaCard
import org.jetbrains.compose.resources.StringResource

data class UiOverview(
    val category: MediaCategory,
    val title: StringResource?,
    val items: List<UiMediaCard>
)