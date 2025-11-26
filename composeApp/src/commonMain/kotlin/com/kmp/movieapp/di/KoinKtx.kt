package com.kmp.movieapp.di

import com.kmp.kmpnavigation.di.navigationModule
import com.kmp.movieapp.core.di.coreModule
import com.kmp.movieapp.movie.di.movieModule
import com.kmp.series.di.settingsModule
import org.koin.core.KoinApplication

fun KoinApplication.initModules() {
    modules(
        modules = listOf(appModule, navigationModule, coreModule, movieModule, settingsModule)
    )
}