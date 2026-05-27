package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.network.http.addDefaultRequest
import com.kmp.movieapp.core.network.http.getHttpClientEngine
import com.kmp.movieapp.core.network.http.installHttpCache
import com.kmp.movieapp.core.network.http.installJson
import com.kmp.movieapp.core.network.http.installLogging
import com.kmp.movieapp.core.network.http.installResources
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.NavigatorImpl
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.navigatorQualifier
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
        NavigatorImpl(startDestination = AppNavigation.HomeRoute)
    }

    single<Navigator<HomeNavigation>>(qualifier = navigatorQualifier<HomeNavigation>()) {
        NavigatorImpl(startDestination = HomeNavigation.InitialScreenRoute)
    }

    single<Navigator<BrowseNavigation>>(qualifier = navigatorQualifier<BrowseNavigation>()) {
        NavigatorImpl(startDestination = BrowseNavigation.InitialScreenRoute)
    }

}