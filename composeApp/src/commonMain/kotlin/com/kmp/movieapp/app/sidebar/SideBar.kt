package com.kmp.movieapp.app.sidebar

import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kmp.movieapp.app.navigation.destination.AppRootSection
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberActiveChildSection
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.home_screen
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SideBar() {
    val navigation = rememberNavigation()
    val active = rememberActiveChildSection(
        parentSection = AppRootSection, initialChild = HomeScreenSection
    )

    NavigationRail {
        NavigationRailItem(
            selected = active == HomeScreenSection,
            onClick = { navigation.switchTo(HomeScreenSection) },
            icon = {
//                Icon(
//                    painter = painterResource(Res.drawable.ic_home), contentDescription = null
//                )
            },
            label = { Text(stringResource(Res.string.home_screen)) }
        )
        NavigationRailItem(
            selected = active == SettingsSection,
            onClick = { navigation.switchTo(SettingsSection) },
            icon = {
//                Icon(
//                    painter =, contentDescription = null
//                )
            },
            label = { Text(stringResource(Res.string.settings_screen_title)) })
    }
}
