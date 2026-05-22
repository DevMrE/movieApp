package com.kmp.movieapp.app_screen.mobile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.app_bar.bottombar.BottomBarComponent
import com.kmp.movieapp.app_bar.topbar.TopAppBarComponent
import com.kmp.movieapp.navigation.AppContent

@Composable
fun AppScreen() {

    Scaffold(
        topBar = {
            TopAppBarComponent()
        },
        bottomBar = {
            BottomBarComponent()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val layoutDir = LocalLayoutDirection.current
        val paddingWithoutBottom = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDir),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDir),
            bottom = 0.dp
        )

        AppContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingWithoutBottom)
        )
    }
}