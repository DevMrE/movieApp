package com.kmp.movieapp.core.network.http

import com.kmp.movieapp.core.network.model.ApiError
import com.kmp.movieapp.core.network.util.Result
import io.ktor.http.HttpStatusCode

object HandleError {

    fun getResultForHttpStatus(statusCode: HttpStatusCode?): Result.Failure<ApiError> {
        val error = when (statusCode) {
            HttpStatusCode.NonAuthoritativeInformation -> ApiError.UserUnauthorized
            HttpStatusCode.NotFound -> ApiError.NotFound
            // TODO: Handle more status codes

            else -> ApiError.Unknown
        }

        return Result.Failure(value = error)
    }

    fun getResultForException(throwable: Throwable): Result.Failure<ApiError> {
        val error = when (throwable) {
           is NoSuchElementException -> ApiError.NotFound

            else -> ApiError.Unknown
        }

        return Result.Failure(value = error)
    }
}