package com.kmp.movieapp.core.data.model

sealed interface ApiError {

    data object UserUnauthorized: ApiError
    data object Unknown : ApiError
    data object NotFound : ApiError
}
