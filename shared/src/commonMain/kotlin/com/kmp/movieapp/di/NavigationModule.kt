package com.kmp.movieapp.di

import com.kmp.movieapp.components.app_bar.bottombar.di.bottomBarNavigationModule
import com.kmp.movieapp.discover.di.discoverNavigationModule
import com.kmp.movieapp.search.di.searchNavigationModule
import org.koin.dsl.module

val navigationModule = module {
    includes(bottomBarNavigationModule)
    includes(discoverNavigationModule)
    includes(searchNavigationModule)
}