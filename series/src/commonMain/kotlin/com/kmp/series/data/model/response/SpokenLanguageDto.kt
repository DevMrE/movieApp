package com.kmp.series.data.model.response


import kotlinx.serialization.SerialName

data class SpokenLanguageDto(
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("name")
    val name: String? = null
)