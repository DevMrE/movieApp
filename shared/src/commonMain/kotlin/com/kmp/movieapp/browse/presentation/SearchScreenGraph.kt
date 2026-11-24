package com.kmp.movieapp.browse.presentation

import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

object SearchScreenGraph {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    operator fun invoke() {
        val navigator = koinNavigation<BrowseNavigation>()
        val entryProvider = koinEntryProvider<BrowseNavigation>()

        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider
        )
    }
}
