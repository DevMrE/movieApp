package com.kmp.movieapp.movie.data.model.response.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductionCountryResponse(
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("name")
    val name: String? = null
)