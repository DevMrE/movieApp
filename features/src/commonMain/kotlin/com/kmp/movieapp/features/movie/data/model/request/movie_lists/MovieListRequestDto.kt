package com.kmp.movieapp.features.movie.data.model.request.movie_lists

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_ENDPOINT}/popular")
internal data class MovieListRequestDto(
    val page: Int = 1,
)