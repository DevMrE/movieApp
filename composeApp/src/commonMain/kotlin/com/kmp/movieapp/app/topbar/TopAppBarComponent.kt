package com.kmp.movieapp.app.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.topbar.component.TopAppBarContent
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.app_name
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBarComponent() {
    val navSection = rememberActiveTabIn<BottomBarTabs>()

    val screenStringResource = when (navSection) {
        SettingsDestination -> Res.string.settings_screen_title
        else -> Res.string.app_name
    }

    TopAppBarContent(title = stringResource(screenStringResource))
}

@Preview
@Composable
private fun TopAppBarComponentPreview() {
    TopAppBarContent(title = "MovieApp", showBackButton = true)
}