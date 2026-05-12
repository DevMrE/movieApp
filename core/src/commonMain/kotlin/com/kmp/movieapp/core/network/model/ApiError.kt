package com.kmp.movieapp.core.network.model

sealed interface ApiError {

    data object UserUnauthorized: ApiError
    data object Unknown : ApiError
    data object NotFound : ApiError
    data object RateLimit : ApiError
}
