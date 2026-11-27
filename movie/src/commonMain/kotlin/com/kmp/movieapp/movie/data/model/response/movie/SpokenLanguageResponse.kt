package com.kmp.movieapp.movie.data.model.response.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SpokenLanguageResponse(
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("name")
    val name: String? = null
)