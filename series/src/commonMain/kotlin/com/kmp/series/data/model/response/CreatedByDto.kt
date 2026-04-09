package com.kmp.series.data.model.response


import kotlinx.serialization.SerialName

data class CreatedByDto(
    @SerialName("credit_id")
    val creditId: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    @SerialName("profile_path")
    val profilePath: String? = null
)