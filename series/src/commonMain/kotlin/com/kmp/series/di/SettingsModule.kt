package com.kmp.series.di

import com.kmp.series.presentation.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsScreenViewModel)
}