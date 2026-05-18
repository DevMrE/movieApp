package com.kmp.movieapp.series.data.model.request.series_list

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/tv/popular")
data class SeriesRequestDto(
    val page: Int = 1,
)
