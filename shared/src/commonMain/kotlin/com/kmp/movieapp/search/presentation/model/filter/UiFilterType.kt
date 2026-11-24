package com.kmp.movieapp.search.presentation.model.filter

import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.filter_by_genre
import org.jetbrains.compose.resources.StringResource

sealed class UiFilterType {

    data class Genre(
        val genres: List<UiGenre>,
        val title: StringResource = Res.string.filter_by_genre,
    ) : UiFilterType()
}