package com.kmp.movieapp.components.app_screen.mobile

import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.ui.theme.AppTheme
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MobileAppScreen() {
    AppTheme {
      MobileScreen()
    }
}