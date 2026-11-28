package com.kmp.movieapp.components.app_screen.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.bottombar.BottomBarComponent
import com.kmp.movieapp.components.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.components.topbar.util.TopBarConfig
import com.kmp.movieapp.components.topbar.util.rememberTopBarConfig
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MobileScreen() {
    val navigator = koinNavigation<BottomBarRoute>()
    val entryProvider = koinEntryProvider<BottomBarRoute>()

    val discoverConfig = rememberTopBarConfig<DiscoverRoute>()
    val searchConfig = rememberTopBarConfig<SearchRoute>()
    val profileConfig = rememberTopBarConfig<BottomBarRoute>()

    val topBarConfig = when (navigator.backStack.lastOrNull()) {
        is BottomBarRoute.DiscoverRoute -> discoverConfig
        is BottomBarRoute.SearchRoute -> searchConfig
        is BottomBarRoute.Profile -> profileConfig
        else -> TopBarConfig()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBarComponent()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider,
        )
    }
}