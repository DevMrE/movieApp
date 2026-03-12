package com.kmp.movieapp.di

import com.kmp.movieapp.app.search.SearchViewModel
import com.kmp.movieapp.homescreen.HomeScreenViewModel
import com.kmp.movieapp.settings.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::SettingsScreenViewModel)
    viewModelOf(::SearchViewModel)
}