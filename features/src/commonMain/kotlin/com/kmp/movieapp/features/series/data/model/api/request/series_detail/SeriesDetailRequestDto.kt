package com.kmp.movieapp.features.series.data.model.api.request.series_detail

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.SERIES_ENDPOINT}/{seriesId}")
data class SeriesDetailRequestDto(
    val seriesId: Int,
)