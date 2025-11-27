package com.kmp.movieapp.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kmp.movieapp.app.bottombar.BottomBarComponent
import com.kmp.movieapp.app.component.MovieAppContentComponent
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme

@Composable
fun MovieAppScreenComponent() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBarComponent()
            },
            bottomBar = {
                BottomBarComponent()
            }
        ) { paddingValues ->
            MovieAppContentComponent(paddingValues)
        }
    }
}