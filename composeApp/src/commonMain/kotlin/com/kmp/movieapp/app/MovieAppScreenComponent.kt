package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.app.bottombar.BottomBarComponent
import com.kmp.movieapp.app.navigation.destination.BottomBarSection
import com.kmp.movieapp.app.sidebar.SideBar
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.navigation.NavigationBarPosition
import com.kmp.navigation.compose.AdaptiveSectionScaffold
import com.kmp.navigation.compose.NavigationContent


@Composable
fun AppScreen() {
    AdaptiveSectionScaffold(
        parentSection = BottomBarSection,
        navigationBar = { _, strategy ->
            when (strategy.navBarPosition) {
                NavigationBarPosition.Bottom -> BottomBarComponent()
                NavigationBarPosition.Left -> SideBar()
                NavigationBarPosition.None -> {}
            }
        },
        fallbackContent = { /* fallback */ }
    )
}

@Composable
fun AppContent() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBarComponent()
            },
        ) { paddingValues ->
            NavigationContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        }
    }
}