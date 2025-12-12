package com.kmp.movieapp.app.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavSection
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.home
import movieapp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomBarComponent() {
    val navigation = rememberNavigation()
    val navSection = rememberNavSection(initialSection = HomeScreenSection)

    Column {
        HorizontalDivider(
            thickness = MaterialTheme.size.bottomBarStrokeHeight,
            color = MaterialTheme.colorScheme.surfaceVariant
        )

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            NavigationBarItem(
                selected = navSection == HomeScreenSection,
                onClick = { navigation.switchTo(HomeScreenSection) },
                icon = {
                    Icon(
                        imageVector = vectorResource(resource = Res.drawable.home),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary
                )
            )

            NavigationBarItem(
                selected = navSection == SettingsSection,
                onClick = { navigation.switchTo(SettingsSection) },
                icon = {
                    Icon(
                        imageVector = vectorResource(resource = Res.drawable.settings),
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