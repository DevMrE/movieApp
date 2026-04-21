package com.kmp.movieapp.features.home.presentation.model

import androidx.compose.runtime.Stable
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import org.jetbrains.compose.resources.StringResource

@Stable
internal data class UiHomeList(
    val category: HomeCategory,
    val title: StringResource,
    val movies: List<UiMedia>,
)
