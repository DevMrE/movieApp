package com.kmp.movieapp.features.series.data.model.api.response.series_detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)