package com.kmp.movieapp.series.data.model.request.series_detail

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/tv/{seriesId}")
data class SeriesDetailRequestDto(
    val seriesId: Int?,
)