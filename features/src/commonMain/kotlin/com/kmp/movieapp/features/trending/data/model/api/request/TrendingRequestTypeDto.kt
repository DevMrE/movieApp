package com.kmp.movieapp.features.trending.data.model.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TrendingRequestTypeDto {
    @SerialName("all")
    ALL,
    @SerialName("movie")
    MOVIE,
    @SerialName("tv")
    SERIES,
    @SerialName("people")
    PEOPLE;

}