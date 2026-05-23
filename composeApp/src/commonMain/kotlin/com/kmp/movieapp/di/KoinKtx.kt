package com.kmp.movieapp.di

import com.kmp.movieapp.content_detail.di.contentDetailModule
import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.device_operations.di.deviceModule
import com.kmp.movieapp.discover.di.discoverModule
import com.kmp.movieapp.navigation.di.navigationModule
import com.kmp.movieapp.search.di.searchModule
import org.koin.core.KoinApplication

fun KoinApplication.initModules() {
    modules(
        modules = listOf(
            appModule,
            coreModule,
            featureModule,
            contentDetailModule,
            searchModule,
            deviceModule(),
            navigationModule,
            discoverModule,
        )
    )
}