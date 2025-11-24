package com.kmp.movieapp.core.data.http

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LoggingFormat

fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        format = LoggingFormat.OkHttp
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.i(tag = "network", messageString = message)
            }
        }
    }
}