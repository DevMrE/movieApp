package com.kmp.movieapp.features.home.data.model.request.movie_lists

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_ENDPOINT}/{movieListCategory}")
internal data class MovieListRequestDto(
    val page: Int = 1,
    val movieListCategory: String,
)