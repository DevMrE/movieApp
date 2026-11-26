package com.kmp.movieapp.components.app_screen

import androidx.compose.runtime.Composable
import com.kmp.movieapp.components.app_screen.mobile.MobileScreen
import com.kmp.movieapp.core.ui.theme.AppTheme

@Composable
actual fun PlatformScreen() {
    AppTheme {
        MobileScreen()
    }
}