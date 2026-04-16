package com.kmp.movieapp.features.home.data.model.request.genre

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.resources.Resource

@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_GENRE_ENDPOINT}")
internal data class MovieGenreRequestDto(
    val language: String? = null
)