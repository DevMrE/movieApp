package com.kmp.movieapp.series.data.model.api.request.series_list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SeriesEndpointCategory {
    @SerialName("popular")
    POPULAR
}