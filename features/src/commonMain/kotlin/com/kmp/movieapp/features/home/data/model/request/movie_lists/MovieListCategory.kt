package com.kmp.movieapp.features.home.data.model.request.movie_lists

import kotlinx.serialization.Serializable

@Serializable
enum class MovieListCategory(val category: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    NOW_PLAYING("now_playing")
}