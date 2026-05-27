@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.kmp.movieapp.components.app_screen.mobile

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import com.kmp.movieapp.components.app_bar.bottombar.BottomBarComponent
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.util.koinNavigation
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MobileScreen() {
    val navigator = koinNavigation<AppNavigation>()
    val entryProvider = koinEntryProvider<AppNavigation>()

    Scaffold(
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