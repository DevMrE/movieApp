package com.kmp.movieapp.core.data.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.resources.Resources


fun HttpClientConfig<*>.installResources() {
    install(Resources)
}