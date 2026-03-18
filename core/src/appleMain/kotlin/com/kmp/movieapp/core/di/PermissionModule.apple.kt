package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.open_settings.SettingsNavigator
import com.kmp.movieapp.core.permission.domain.IOSLocationProvider
import com.kmp.movieapp.core.permission.domain.IOSPermissionsController
import com.kmp.movieapp.core.permission.domain.PermissionsController
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {

    single { IOSLocationProvider() }

    single<PermissionsController> {
        IOSPermissionsController(
            locationProvider = get()
        )
    }

    factory<SettingsNavigator> {
        SettingsNavigator()
    }
}