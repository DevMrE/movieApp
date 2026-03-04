package com.kmp.movieapp.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.kmp.movieapp.app.bottombar.BottomBarWithAppContent
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.movieapp.movie.presentation.content.destination.PopularMovieDestination
import com.kmp.navigation.compose.NavigationContent
import com.kmp.navigation.compose.NavigationRoot
import com.kmp.navigation.compose.NavigationTabs
import com.kmp.navigation.compose.rememberCurrentDestination

@Composable
fun MobileAppScreen() {
    AppTheme {
        NavigationRoot {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val navDestination = rememberCurrentDestination()
    Scaffold(
        topBar = {
            TopAppBarComponent()
        },
        bottomBar = {
            BottomBarWithAppContent()
        }
    ) { paddingValues ->
        val layoutDir = LocalLayoutDirection.current
        val paddingWithoutBottom = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDir),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDir),
            bottom = 0.dp
        )

        Logger.i("KmpNavigation", message = { "Destination: $navDestination" })
        NavigationTabs<BottomBarTabs>(Modifier.fillMaxSize().padding(paddingWithoutBottom))
        NavigationContent<PopularMovieDestination>(Modifier.padding(paddingValues))
    }
}