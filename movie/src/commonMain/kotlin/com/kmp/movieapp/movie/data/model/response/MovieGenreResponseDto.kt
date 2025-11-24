package com.kmp.movieapp.movie.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreResponseDto(
    @SerialName("genres")
    val genres: List<GenreDto>? = emptyList()
)
