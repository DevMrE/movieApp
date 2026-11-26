package com.kmp.movieapp.components.topbar.mapper

import com.kmp.movieapp.core.ui.content.model.MediaCategory
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.popular_movies_title
import com.kmp.movieapp.shared.popular_series_title
import org.jetbrains.compose.resources.StringResource
import org.koin.core.module.Module

context(_: Module)
fun MediaCategory.titleRes(): StringResource? =
    when (this) {
        MediaCategory.SERIES -> Res.string.popular_series_title
        MediaCategory.MOVIE -> Res.string.popular_movies_title
        else -> null
    }
