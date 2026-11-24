package com.kmp.movieapp.discover.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.core.util.navigation.route.DiscoverRoute
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

object DiscoverScreenGraph {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    operator fun invoke() {
        val navigator = koinNavigation<DiscoverRoute>()
        val entryProvider = koinEntryProvider<DiscoverRoute>()

        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider
        )
    }
}