package com.kmp.movieapp

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.movieapp.app.MovieAppScreenComponent
import com.kmp.movieapp.app.navigation.registerAppNavigation
import com.kmp.movieapp.di.initModules
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {

    startKoin {
        initModules()
    }
    registerAppNavigation()
    
    MovieAppScreenComponent()
}