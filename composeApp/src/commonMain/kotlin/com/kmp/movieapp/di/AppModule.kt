package com.kmp.movieapp.di

import com.kmp.movieapp.browse.presentation.BrowseNavigationScreen
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.home.presentation.HomeNavigationScreen
import com.kmp.movieapp.settings.SettingsContent
import com.kmp.movieapp.settings.SettingsScreenViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val appModule = module {
    viewModelOf(::SettingsScreenViewModel)

    // AppNavigation
    navigation<AppNavigation.HomeRoute> {
        HomeNavigationScreen()
    }

    navigation<AppNavigation.BrowseRoute> {
        BrowseNavigationScreen()
    }

    navigation<AppNavigation.MoreRoute> {
        SettingsContent()
    }
}