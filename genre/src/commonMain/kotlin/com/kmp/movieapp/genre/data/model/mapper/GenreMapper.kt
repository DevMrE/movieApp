package com.kmp.movieapp.genre.data.model.mapper

import com.kmp.movieapp.genre.data.model.response.GenreDto
import com.kmp.movieapp.genre.domain.model.Genre

private fun GenreDto.toGenre() = Genre(
    id = id ?: 0,
    name = "$name"
)

internal fun List<GenreDto>.toGenreList() = map {
    it.toGenre()
}