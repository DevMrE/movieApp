package com.kmp.movieapp.features.movie.data.model.response

import com.kmp.movieapp.core.network.model.Dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreResponseDto(
    @SerialName("genres")
    val genres: List<GenreDto>? = emptyList()
) : Dto
