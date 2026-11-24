package com.kmp.movieapp.core.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.core.network.http.addDefaultRequest
import com.kmp.movieapp.core.network.http.getHttpClientEngine
import com.kmp.movieapp.core.network.http.installHttpCache
import com.kmp.movieapp.core.network.http.installJson
import com.kmp.movieapp.core.network.http.installLogging
import com.kmp.movieapp.core.network.http.installResources
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import io.ktor.client.HttpClient
import io.ktor.client.plugins.bomremover.BOMRemover
import org.koin.dsl.module

val coreModule = module {
    includes(corePlatformModule)

    single<HttpClient> {
        HttpClient(getHttpClientEngine()) {
            install(BOMRemover)
            installHttpCache()
            installResources()
            installJson()
            installLogging()
            addDefaultRequest(get())
        }
    }

    single<Navigator<AppNavigation>>(qualifier = navigatorQualifier<AppNavigation>()) {
        RegisterNavigationGraph(startDestination = AppNavigation.DiscoverRoute)
    }

}