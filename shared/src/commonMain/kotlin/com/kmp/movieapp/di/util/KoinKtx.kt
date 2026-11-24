package com.kmp.movieapp.di.util

import com.kmp.movieapp.content_detail.di.contentDetailModule
import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.device_operations.di.deviceModule
import com.kmp.movieapp.di.appModule
import com.kmp.movieapp.di.featureModule
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
            deviceModule,
        )
    )
}