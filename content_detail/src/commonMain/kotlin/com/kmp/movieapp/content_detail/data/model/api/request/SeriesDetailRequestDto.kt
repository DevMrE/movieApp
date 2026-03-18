package com.kmp.movieapp.content_detail.data.model.api.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.SERIES_ENDPOINT}/{series_id}")
data class SeriesDetailRequestDto(
    @SerialName("series_id")
    val movieId: Int,
    @SerialName("language")
    val language: String? = "en",
    @SerialName("append_to_response")
    val appendStringResponse: List<String> = listOf("videos")
)
