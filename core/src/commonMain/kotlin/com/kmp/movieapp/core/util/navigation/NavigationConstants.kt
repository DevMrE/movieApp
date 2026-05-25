package com.kmp.movieapp.core.util.navigation

import org.koin.core.qualifier.named

object NavigationConstants {

    val APP_NAVIGATOR = named("app_navigator")
    val HOME_NAVIGATOR = named("home_navigator")
    val DISCOVER_NAVIGATOR = named("discover_navigator")
}