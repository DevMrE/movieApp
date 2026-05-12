package com.kmp.movieapp.discover.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("${UrlHelper.API_VERSION_PATH}/discover/movies")
@Serializable
data class DiscoverMoviesRequestDto(
    val page: Int = 1,
)
