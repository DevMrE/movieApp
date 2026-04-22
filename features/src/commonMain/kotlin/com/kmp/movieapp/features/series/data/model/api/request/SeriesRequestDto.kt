package com.kmp.movieapp.features.series.data.model.api.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.SERIES_ENDPOINT}/popular}")
data class SeriesRequestDto(
    val page: Int = 1,
)
