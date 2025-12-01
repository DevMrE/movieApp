package com.kmp.movieapp.app.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
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

    NavigationBar {
        NavigationBarItem(
            selected = navSection == HomeScreenSection,
            onClick = { navigation.switchTo(HomeScreenSection) },
            icon = {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.movie),
                    contentDescription = null
                )
            },
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
        )
    }
}