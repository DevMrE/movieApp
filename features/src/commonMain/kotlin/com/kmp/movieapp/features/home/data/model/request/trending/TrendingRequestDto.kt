package com.kmp.movieapp.features.home.data.model.request.trending

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.TRENDING_ENDPOINT}/{path}/{time_window}")
data class TrendingRequestDto(
    @SerialName("path")
    val trendingRequestType: TrendingRequestTypeDto,
    @SerialName("time_window")
    val timeWindow : String = "week",
)
