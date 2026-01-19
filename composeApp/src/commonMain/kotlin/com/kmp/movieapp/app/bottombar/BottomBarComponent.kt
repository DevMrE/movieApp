package com.kmp.movieapp.app.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_home
import movieapp.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomBarComponent() {
    val navigation = rememberNavigation()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            thickness = MaterialTheme.size.bottomBarStrokeHeight,
            color = MaterialTheme.colorScheme.surfaceVariant
        )

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.errorContainer
        ) {
            NavigationBarItem(
                selected = true,
                onClick = { navigation.switchTo(HomeScreenSection) },
                icon = {
                    Icon(
                        imageVector = vectorResource(resource = Res.drawable.ic_home),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                )
            )

            NavigationBarItem(
                selected = false,
                onClick = { navigation.switchTo(SettingsSection) },
                icon = {
                    Icon(
                        imageVector = vectorResource(resource = Res.drawable.ic_settings),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}