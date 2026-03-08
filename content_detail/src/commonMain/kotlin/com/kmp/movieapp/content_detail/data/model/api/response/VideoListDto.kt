package com.kmp.movieapp.content_detail.data.model.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListDto(
    @SerialName("results")
    val results: List<VideoResultDto?>? = null
)