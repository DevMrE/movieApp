package com.kmp.movieapp.discover.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Resource("${UrlHelper.API_V3}${UrlHelper.DISCOVER_MOVIE_ENDPOINT}")
@Serializable
data class DiscoverMoviesRequestDto(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("with_genres")
    val genres: String? = null
)
