package com.kmp.movieapp.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDto<DTO: Dto>(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<DTO?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)