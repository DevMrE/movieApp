package com.kmp.movieapp.app_screen.mobile

import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.theme.AppTheme
import com.kmp.navigation.compose.NavigationRoot

@Composable
fun MobileAppScreen() {
    AppTheme {
        NavigationRoot {
            AppContent()
        }
    }
}