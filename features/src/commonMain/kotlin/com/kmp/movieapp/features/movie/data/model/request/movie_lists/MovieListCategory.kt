package com.kmp.movieapp.features.movie.data.model.request.movie_lists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MovieListCategory {
    @SerialName("popular")
    POPULAR,

    @SerialName("top_rated")
    TOP_RATED,

    @SerialName("upcoming")
    UPCOMING,

    @SerialName("now_playing")
    NOW_PLAYING
}