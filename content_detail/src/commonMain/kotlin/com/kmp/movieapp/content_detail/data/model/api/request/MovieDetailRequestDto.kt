package com.kmp.movieapp.content_detail.data.model.api.request

import com.kmp.movieapp.core.data.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_ENDPOINT}/{movie_id}")
data class MovieDetailRequestDto(
    @SerialName("movie_id")
    val movieId: Int,
    @SerialName("language")
    val language: String? = "en",
    @SerialName("append_to_response")
    val appendStringResponse: List<String> = listOf("videos")
)
