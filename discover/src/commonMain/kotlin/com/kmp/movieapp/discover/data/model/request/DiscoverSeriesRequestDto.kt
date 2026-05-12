package com.kmp.movieapp.discover.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource

@Resource("${UrlHelper.API_VERSION_PATH}/discover/tv")
data class DiscoverSeriesRequestDto(
    val page: Int = 1
)
