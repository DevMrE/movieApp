package com.kmp.series.data.model.response


import kotlinx.serialization.SerialName

data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("name")
    val name: String? = null
)