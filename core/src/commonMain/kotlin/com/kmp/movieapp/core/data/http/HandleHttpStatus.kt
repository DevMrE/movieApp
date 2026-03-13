package com.kmp.movieapp.core.data.http

import com.kmp.movieapp.core.data.model.ApiError
import com.kmp.movieapp.core.util.network.Result
import io.ktor.http.HttpStatusCode

object HandleHttpStatus {

    fun getResultForStatus(statusCode: HttpStatusCode?): Result.Failure<ApiError> {
        val error = when (statusCode) {
            HttpStatusCode.NonAuthoritativeInformation -> ApiError.UserUnauthorized
            HttpStatusCode.NotFound -> ApiError.NotFound
            // TODO: Handle more status codes

            else -> ApiError.Unknown
        }

        return Result.Failure(value = error)
    }
}