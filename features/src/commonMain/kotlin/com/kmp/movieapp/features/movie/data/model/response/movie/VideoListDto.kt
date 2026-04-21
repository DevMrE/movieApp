package com.kmp.movieapp.features.movie.data.model.response.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListDto(
    @SerialName("results")
    val results: List<VideoResultDto?>? = null
)