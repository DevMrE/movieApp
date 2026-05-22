package com.kmp.movieapp.di

import com.kmp.movieapp.content_detail.di.contentDetailModule
import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.device_operations.di.deviceModule
import com.kmp.movieapp.discover.di.discoverModule
import com.kmp.movieapp.navigation.di.homeModule
import com.kmp.movieapp.search.di.searchModule
import com.kmp.navigation.di.navigationModule
import org.koin.core.KoinApplication

fun KoinApplication.initModules() {
    modules(
        modules = listOf(
            appModule,
            navigationModule,
            coreModule,
            featureModule,
            contentDetailModule,
            searchModule,
            deviceModule(),
            homeModule,
            discoverModule,
        )
    )
}