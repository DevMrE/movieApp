package com.kmp.movieapp.core.network.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.cache.HttpCache

fun HttpClientConfig<*>.installHttpCache() {
    install(HttpCache)
}