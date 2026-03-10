package com.kmp.movieapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.kmp.movieapp.movie.presentation.destination.MovieCategoryListDestination
import com.kmp.navigation.compose.NavigationContent
import com.kmp.navigation.compose.NavigationRoot
import com.kmp.navigation.compose.NavigationTabs
import com.kmp.navigation.compose.rememberNavDestination

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
    val navDestination = rememberNavDestination()
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingWithoutBottom)
        ) {
            NavigationTabs<BottomBarTabs>()
            NavigationContent<MovieCategoryListDestination>()
        }
    }
}