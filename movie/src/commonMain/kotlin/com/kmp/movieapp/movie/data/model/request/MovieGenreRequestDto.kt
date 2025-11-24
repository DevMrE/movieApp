package com.kmp.movieapp.movie.data.model.request

import com.kmp.movieapp.core.data.url.UrlHelper
import io.ktor.resources.Resource

@Resource("${UrlHelper.API_VERSION_PATH}${UrlHelper.MOVIE_GENRE_ENDPOINT}")
data class MovieGenreRequestDto(
    val language: String? = null
)
