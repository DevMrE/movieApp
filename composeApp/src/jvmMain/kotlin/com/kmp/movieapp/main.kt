package com.kmp.movieapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    startKoin{
        initModules()
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "MovieApp",
    ) {
        App()
    }
}