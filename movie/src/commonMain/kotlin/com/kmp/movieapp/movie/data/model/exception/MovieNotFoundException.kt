package com.kmp.movieapp.movie.data.model.exception

import com.kmp.movieapp.core.data.model.ApiError

class MovieNotFoundException: Throwable(
    message = "Movie not found"
)

sealed class MovieError : ApiError {
    data object NotFound: MovieError()

}

sealed class GenerelError: MovieError() {
    data object Muhahaha: GenerelError()
}