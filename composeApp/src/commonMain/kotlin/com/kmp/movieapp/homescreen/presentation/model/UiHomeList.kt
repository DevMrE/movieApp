package com.kmp.movieapp.homescreen.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import org.jetbrains.compose.resources.StringResource

@Stable
internal data class UiHomeList(
    val category: HomeCategory,
    val title: StringResource,
    val movies: List<UiMediaCard>,
)
