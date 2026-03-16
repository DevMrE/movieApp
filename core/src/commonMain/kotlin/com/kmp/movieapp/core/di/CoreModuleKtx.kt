package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.data.http.addDefaultRequest
import com.kmp.movieapp.core.data.http.getHttpClientEngine
import com.kmp.movieapp.core.data.http.installHttpCache
import com.kmp.movieapp.core.data.http.installJson
import com.kmp.movieapp.core.data.http.installLogging
import com.kmp.movieapp.core.data.http.installResources
import io.ktor.client.HttpClient
import io.ktor.client.plugins.bomremover.BOMRemover
import org.koin.dsl.module

val coreModule = module {
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

    includes(sharedCoreModule)
}