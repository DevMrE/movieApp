package com.kmp.movieapp.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navigator: Navigator<Route> = koinInject()
    val entryProvider = koinEntryProvider<Any>()

    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        entryProvider = entryProvider,
    )
}