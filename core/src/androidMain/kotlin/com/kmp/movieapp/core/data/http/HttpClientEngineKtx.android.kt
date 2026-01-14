package com.kmp.movieapp.core.data.http

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.ConnectionSpec
import okhttp3.TlsVersion

actual fun getHttpClientEngine(): HttpClientEngine = OkHttp.create {
    config {
        val modern = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
            .build()

        connectionSpecs(listOf(modern, ConnectionSpec.COMPATIBLE_TLS))
    }
}
