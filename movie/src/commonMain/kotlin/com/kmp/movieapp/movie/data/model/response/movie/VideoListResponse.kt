package com.kmp.movieapp.movie.data.model.response.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VideoListResponse(
    @SerialName("results")
    val results: List<VideoResultResponse?>? = null
)