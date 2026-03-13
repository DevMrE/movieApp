package com.kmp.movieapp.movie.data.model.request

import kotlinx.serialization.Serializable

@Serializable
internal enum class MovieListCategory(val category: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    NOW_PLAYING("now_playing")
}