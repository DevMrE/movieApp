package com.kmp.movieapp.core.network.http

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import io.ktor.http.HttpStatusCode

object HandleError {

    fun getResultForHttpStatus(statusCode: HttpStatusCode?): Result.Failure<ApiError> {
        val error = getErrorForStatusCode(statusCode)

        return Result.Failure(value = error)
    }

    private fun getErrorForStatusCode(statusCode: HttpStatusCode?): ApiError = when (statusCode) {
        HttpStatusCode.Unauthorized -> ApiError.UserUnauthorized
        HttpStatusCode.Forbidden -> ApiError.UserUnauthorized
        HttpStatusCode.NotFound -> ApiError.NotFound
        HttpStatusCode.TooManyRequests -> ApiError.RateLimit
        // TODO: Handle more status codes

        else -> ApiError.Unknown
    }

    fun getResultForException(throwable: Throwable): Result.Failure<ApiError> {
        val error = when (throwable) {
            is NoSuchElementException -> ApiError.NotFound

            else -> ApiError.Unknown
        }

        return Result.Failure(value = error)
    }
}