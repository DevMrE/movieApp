package com.kmp.movieapp.movie.data.model.response.movie


import com.kmp.movieapp.core.network.model.Dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductionCompanyResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
) : Dto