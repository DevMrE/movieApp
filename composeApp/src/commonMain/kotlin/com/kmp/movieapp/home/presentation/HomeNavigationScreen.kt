package com.kmp.movieapp.home.presentation

import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

object HomeNavigationScreen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    operator fun invoke() {
        val navigator = koinNavigation<HomeNavigation>()
        val entryProvider = koinEntryProvider<HomeNavigation>()

        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider
        )
    }
}