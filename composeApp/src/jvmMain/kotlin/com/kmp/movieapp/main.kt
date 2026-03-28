package com.kmp.movieapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.di.initModules
import com.kmp.movieapp.navigation.registerAppNavigation
import org.koin.core.context.startKoin

fun main() = application {

    startKoin {
        initModules()
    }

    registerAppNavigation()

    Window(
        onCloseRequest = ::exitApplication,
        title = "MovieApp",
    ) {
        MobileAppScreen()
    }
}