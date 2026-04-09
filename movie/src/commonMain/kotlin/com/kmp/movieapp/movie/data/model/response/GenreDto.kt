package com.kmp.movieapp.movie.data.model.response

import com.kmp.movieapp.core.network.model.Dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
) : Dto
