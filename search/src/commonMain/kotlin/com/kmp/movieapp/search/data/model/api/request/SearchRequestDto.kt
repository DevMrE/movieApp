package com.kmp.movieapp.search.data.model.api.request

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Resource("${UrlHelper.API_V3}${UrlHelper.SEARCH_ENDPOINT}")
@Serializable
data class SearchRequestDto(
    val query: String
)
