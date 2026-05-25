package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.language.IosLocaleLanguageProviderImpl
import com.kmp.movieapp.core.language.LocaleLanguageProvider
import com.kmp.movieapp.core.open_settings.IosSettingsNavigator
import com.kmp.movieapp.core.open_settings.SettingsNavigator
import org.koin.core.module.Module
import org.koin.dsl.module

actual val corePlatformModule: Module = module {

    single<LocaleLanguageProvider> {
        IosLocaleLanguageProviderImpl()
    }

    factory<SettingsNavigator> {
        IosSettingsNavigator()
    }
}