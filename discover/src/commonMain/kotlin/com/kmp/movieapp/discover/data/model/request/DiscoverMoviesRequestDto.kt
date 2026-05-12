package com.kmp.movieapp.discover.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.DISCOVER_MOVIE_ENDPOINT}")
@Serializable
data class DiscoverMoviesRequestDto(
    val page: Int = 1,
)
