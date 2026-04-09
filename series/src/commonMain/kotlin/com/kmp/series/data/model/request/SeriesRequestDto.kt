package com.kmp.series.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.SERIES_ENDPOINT}/{series_id}")
data class SeriesRequestDto(
    @SerialName("series_id")
    val seriesId: Int,
    @SerialName("language")
    val language: String = "en",
    @SerialName("append_to_response")
    val appendVideos: String = "videos"
)
