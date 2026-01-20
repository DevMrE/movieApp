package com.kmp.movieapp.app.topbar

import androidx.compose.runtime.Composable
import com.kmp.detail.presentation.destination.DetailNavDestination
import com.kmp.movieapp.app.topbar.component.TopAppBarContent
import com.kmp.movieapp.core.util.boolean.isTrue
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavSection
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.app_name
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {
    val navSection = rememberNavSection(initialSection = HomeScreenSection)

    val screenStringResource = when (navSection) {
        SettingsSection -> Res.string.settings_screen_title
        else -> Res.string.app_name
    }

    val enableBackNavigation = when (navSection) {
        DetailNavDestination -> false
        else -> true
    }

    if (enableBackNavigation.isTrue) TopAppBarContent(title = stringResource(screenStringResource))
}