package com.kmp.movieapp.genre.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class GenreContentType {
    @SerialName("movie")
    MOVIE,

    @SerialName("tv")
    SERIES
}