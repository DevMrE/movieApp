package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.app.bottombar.BottomBarComponent
import com.kmp.movieapp.app.navigation.destination.AppRootSection
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.navigation.compose.AdaptiveSectionScaffold
import com.kmp.navigation.compose.NavigationContent


@Composable
fun BottomBarTypeComponent() {
    AdaptiveSectionScaffold(
        parentSection = AppRootSection,
        navigationBar = { _, _ ->
            BottomBarComponent()
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