package com.kmp.movieapp.movie.data.model.request.movie_lists

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("${UrlHelper.API_V3}/movie/popular")
internal data class MovieListRequestDto(
    val page: Int = 1,
)