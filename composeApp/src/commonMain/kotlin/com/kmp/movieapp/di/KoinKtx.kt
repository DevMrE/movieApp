package com.kmp.movieapp.di

import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.movie.di.movieModule
import com.kmp.navigation.di.navigationModule
import org.koin.core.KoinApplication

fun KoinApplication.initModules() {
    modules(
        modules = listOf(appModule, navigationModule, movieModule, coreModule)
    )
}