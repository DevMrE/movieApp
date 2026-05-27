package com.kmp.movieapp.genre.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
)