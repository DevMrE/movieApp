package com.kmp.movieapp.core.network.http

import com.kmp.movieapp.core.network.url.UrlHelper
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol

fun HttpClientConfig<*>.addDefaultRequest() {
    defaultRequest {
        url {
            protocolOrNull = URLProtocol.HTTPS
            host = UrlHelper.BASE_URL
        }
        accept(ContentType.Application.Json)
        bearerAuth(UrlHelper.BEARER_TOKEN)
    }
}