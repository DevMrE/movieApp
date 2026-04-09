package com.kmp.movieapp.search.data.model.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaTypeDto {
    @SerialName("movie")
    MOVIE,

    @SerialName("tv")
    TV,

    @SerialName("person")
    PERSON
}