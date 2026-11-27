package com.kmp.movieapp.movie.data.model.request.discover

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/discover/movie")
internal data class FilterMoviesRequest(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("language")
    val language: String? = "en",
    @SerialName("include_adult")
    val includeAdult : Boolean = false,
    @SerialName("region")
    val region : String? = language
)

