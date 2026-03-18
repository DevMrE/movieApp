package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.network.http.addDefaultRequest
import com.kmp.movieapp.core.network.http.getHttpClientEngine
import com.kmp.movieapp.core.network.http.installHttpCache
import com.kmp.movieapp.core.network.http.installJson
import com.kmp.movieapp.core.network.http.installLogging
import com.kmp.movieapp.core.network.http.installResources
import io.ktor.client.HttpClient
import io.ktor.client.plugins.bomremover.BOMRemover
import org.koin.dsl.module

val coreModule = module {
    includes(sharedCoreModule)

    single<HttpClient> {
        HttpClient(getHttpClientEngine()) {
            install(BOMRemover)
            installHttpCache()
            installResources()
            installJson()
            installLogging()

            addDefaultRequest()
        }
    }
}