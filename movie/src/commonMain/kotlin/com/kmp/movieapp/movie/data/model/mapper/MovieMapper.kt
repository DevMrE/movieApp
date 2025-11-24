package com.kmp.movieapp.movie.data.model.mapper

import com.kmp.movieapp.core.data.url.UrlHelper
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.domain.model.Movie

fun MovieDto.toMovie() = Movie(
    id = id ?: 0,
    title = title ?: "",
    posterPath = "${UrlHelper.IMAGE_BASE_URL}$posterPath",
)
