package com.kmp.movieapp.di.util

import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.device_operations.di.deviceModule
import com.kmp.movieapp.di.featureModule
import com.kmp.movieapp.di.moreScreenModule
import com.kmp.movieapp.di.navigationModule
import org.koin.core.KoinApplication

fun KoinApplication.initModules() {
    modules(
        modules = listOf(
            moreScreenModule,
            coreModule,
            navigationModule,
            featureModule,
            deviceModule,
        )
    )
}