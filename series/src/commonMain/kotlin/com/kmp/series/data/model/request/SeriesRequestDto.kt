package com.kmp.series.data.model.request

import com.kmp.movieapp.core.network.model.Dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesRequestDto(
    @SerialName("series_id")
    val seriesId: Int,
    @SerialName("language")
    val language: String? = "en",
    @SerialName("append_to_response")
    val appendVideos: String = "videos"
): Dto
