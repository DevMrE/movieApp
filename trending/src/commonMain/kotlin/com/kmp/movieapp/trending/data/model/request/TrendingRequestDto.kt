package com.kmp.movieapp.trending.data.model.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/trending/{path}/{time_window}")
data class TrendingRequestDto(
    @SerialName("path")
    val trendingRequestType: TrendingRequestTypeDto,
    @SerialName("time_window")
    val timeWindow : String = "week",
)

