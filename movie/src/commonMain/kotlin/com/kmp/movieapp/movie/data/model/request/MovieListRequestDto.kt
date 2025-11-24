package com.kmp.movieapp.movie.data.model.request

import com.kmp.movieapp.core.data.url.UrlHelper
import io.ktor.resources.Resource

@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_ENDPOINT}/{movieListCategory}")
data class MovieListRequestDto(
    val page: Int = 1,
    val region: String? = null,
    val language: String? = null,
    val movieListCategory: String,
)