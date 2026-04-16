package com.kmp.movieapp.features.home.data.model.request.movie

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_ENDPOINT}/{movie_id}")
data class MovieRequestDto(
    @SerialName("movie_id")
    val movieId: Int,
    @SerialName("language")
    val language: String? = "en",
    @SerialName("append_to_response")
    val appendStringResponse: List<String> = listOf("videos")
)
