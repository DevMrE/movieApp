package com.kmp.movieapp.core.data.http

import com.kmp.movieapp.core.data.model.ApiError
import io.ktor.http.HttpStatusCode

object HandleHttpStatus {

    fun getResultForStatus(statusCode: HttpStatusCode) = when (statusCode) {
        HttpStatusCode.NonAuthoritativeInformation -> ApiError.NotFound

        else -> ApiError.Unknown
    }
}