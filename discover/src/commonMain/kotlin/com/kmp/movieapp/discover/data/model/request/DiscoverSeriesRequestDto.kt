package com.kmp.movieapp.discover.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource

@Resource("${UrlHelper.API_V3}${UrlHelper.DISCOVER_SERIES_ENDPOINT}")
data class DiscoverSeriesRequestDto(
    val page: Int = 1
)
