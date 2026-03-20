package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.open_settings.SettingsNavigator
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {
    factory<SettingsNavigator> {
        SettingsNavigator()
    }
}