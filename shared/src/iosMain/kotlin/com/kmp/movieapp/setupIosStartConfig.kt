package com.kmp.movieapp

import com.kmp.movieapp.di.util.initModules
import org.koin.core.context.startKoin

fun setupIosStartConfig() {
    startKoin {
        initModules()
    }
}
