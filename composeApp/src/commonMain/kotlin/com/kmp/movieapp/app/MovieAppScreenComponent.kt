package com.kmp.movieapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.movieapp.app.bottombar.BottomBarWithAppContent
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.navigation.compose.NavigationContent

@Composable
fun AppScreen() {
    AppTheme {
        BottomBarWithAppContent {
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
}