package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.open_settings.SettingsNavigator
import com.kmp.movieapp.core.permission.domain.AndroidGalleryProvider
import com.kmp.movieapp.core.permission.domain.AndroidLocationProvider
import com.kmp.movieapp.core.permission.domain.AndroidPermissionBinder
import com.kmp.movieapp.core.permission.domain.AndroidPermissionsController
import com.kmp.movieapp.core.permission.domain.PermissionsController
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {
    single<AndroidLocationProvider> {
        AndroidLocationProvider()
    }

    single<AndroidGalleryProvider> {
        AndroidGalleryProvider()
    }

    single<AndroidPermissionsController> {
        AndroidPermissionsController(
            androidLocationProvider = get(),
            androidGalleryProvider = get()
        )
    }

    single<PermissionsController> {
        get<AndroidPermissionsController>()
    }

    single<AndroidPermissionBinder> {
        AndroidPermissionBinder(
            permissionsController = get(),
            androidGalleryProvider = get()
        )
    }

    factory<SettingsNavigator> {
        SettingsNavigator()
    }
}