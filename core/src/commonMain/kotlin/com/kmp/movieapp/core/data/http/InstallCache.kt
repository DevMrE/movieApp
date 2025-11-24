package com.kmp.movieapp.core.data.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.cache.HttpCache

fun HttpClientConfig<*>.installHttpCache() {
    install(HttpCache)
}