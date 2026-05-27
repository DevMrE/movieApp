package com.kmp.movieapp.browse.model.filter

import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.filter_by_genre
import org.jetbrains.compose.resources.StringResource

sealed class UiFilterType {

    data class Genre(
        val genres: List<UiGenre>,
        val title: StringResource = Res.string.filter_by_genre,
    ) : UiFilterType()
}