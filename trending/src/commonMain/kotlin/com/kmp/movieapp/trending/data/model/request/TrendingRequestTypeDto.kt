package com.kmp.movieapp.trending.data.model.request

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