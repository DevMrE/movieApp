package com.kmp.movieapp.app

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
import com.kmp.movieapp.app.bottombar.BottomBarWithAppContent
import com.kmp.movieapp.app.topbar.TopAppBarComponent
import com.kmp.movieapp.core.presentation.theme.AppTheme
import com.kmp.navigation.compose.NavigationContent

@Composable
fun AppScreen() {
    AppTheme {
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

            NavigationContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingWithoutBottom),
            )
        }
    }
}