package com.kmp.movieapp.genre.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponseDto(
    @SerialName("genres")
    val genres: List<GenreDto>
)