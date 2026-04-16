package com.kmp.movieapp.features.home.data.model.request.trending

import kotlinx.serialization.Serializable

@Serializable
enum class TrendingRequestTypeDto(val value: String) {
    ALL("all"),
    MOVIE("movie"),
    SERIES("series"),
    PEOPLE("people");

    override fun toString(): String = value
}