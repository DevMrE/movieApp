package com.kmp.movieapp.core.network.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.resources.Resources


fun HttpClientConfig<*>.installResources() {
    install(Resources)
}