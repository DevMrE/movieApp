package com.kmp.movieapp.core.network.http

import com.kmp.movieapp.core.util.logger.logI
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LoggingFormat

fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        format = LoggingFormat.OkHttp
        level = LogLevel.BODY
        logger = object : Logger {
            override fun log(message: String) {
                logI(message = message)
            }
        }
    }
}