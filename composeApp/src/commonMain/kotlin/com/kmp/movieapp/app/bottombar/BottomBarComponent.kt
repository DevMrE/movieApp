package com.kmp.movieapp.app.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavSection
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie
import movieapp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomBarComponent() {
    val navigation = rememberNavigation()
    val navSection = rememberNavSection(initialSection = HomeScreenSection)

    Column {
        HorizontalDivider(Modifier.height(1.dp))
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            NavigationBarItem(
                selected = navSection == HomeScreenSection,
                onClick = { navigation.switchTo(HomeScreenSection) },
                icon = {
                    Icon(
                        imageVector = vectorResource(resource = Res.drawable.movie),
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