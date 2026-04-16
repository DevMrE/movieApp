package com.kmp.movieapp.features.home.presentation.mapper

import com.kmp.movieapp.features.home.domain.model.Movie
import com.kmp.movieapp.features.home.domain.model.HomeCategory
import com.kmp.movieapp.features.home.presentation.model.UiHomeList
import com.kmp.movieapp.features.home.presentation.model.UiMedia

internal fun Movie.toUiMovie() = UiMedia(
    id = id.toString(),
    title = title,
    genre = genres?.joinToString(separator = ", ") { it.name } ?: "",
    posterPath = posterPath,
    backdropPath = backDropPath,
)

internal fun List<Movie>.toUiHomeList(category: HomeCategory): UiHomeList {
    return UiHomeList(
        category = category,
        movies = this.map { it.toUiMovie() }
    )
}

