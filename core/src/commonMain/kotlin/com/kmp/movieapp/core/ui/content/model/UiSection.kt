package com.kmp.movieapp.core.ui.content.model

import org.jetbrains.compose.resources.StringResource

data class UiSection(
    val category: MediaCategory,
    val title: StringResource?,
    val items: List<UiMediaCard>
)