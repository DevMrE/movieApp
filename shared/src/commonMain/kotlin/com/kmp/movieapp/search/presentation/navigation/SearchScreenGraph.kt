package com.kmp.movieapp.search.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.core.util.navigation.route.SearchScreenRoute
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

object SearchScreenGraph {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    operator fun invoke() {
        val navigator = koinNavigation<SearchScreenRoute>()
        val entryProvider = koinEntryProvider<SearchScreenRoute>()

        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider
        )
    }
}
