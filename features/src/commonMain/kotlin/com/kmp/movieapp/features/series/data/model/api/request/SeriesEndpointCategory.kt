package com.kmp.movieapp.features.series.data.model.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SeriesEndpointCategory {
    @SerialName("popular")
    POPULAR
}