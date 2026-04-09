package com.kmp.series.data.model.response


import kotlinx.serialization.SerialName

data class GenreDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)