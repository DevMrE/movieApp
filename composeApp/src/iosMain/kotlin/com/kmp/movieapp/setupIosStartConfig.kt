package com.kmp.movieapp

import com.kmp.movieapp.app.navigation.registerAppNavigation
import com.kmp.movieapp.di.initModules
import org.koin.core.context.startKoin

fun setupIosStartConfig() {
    registerAppNavigation()

    startKoin {
        initModules()
    }
}
