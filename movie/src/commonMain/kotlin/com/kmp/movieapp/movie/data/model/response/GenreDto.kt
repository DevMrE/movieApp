package com.kmp.movieapp.movie.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String
)
