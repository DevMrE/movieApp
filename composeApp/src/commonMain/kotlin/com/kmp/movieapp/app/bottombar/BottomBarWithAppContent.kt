package com.kmp.movieapp.app.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.app.bottombar.component.NoRippleNavItem
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.destination.MovieScreenDestination
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavDestination
import com.kmp.navigation.compose.rememberNavigation
import com.kmp.series.presentation.destination.SeriesScreenDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.home_screen
import movieapp.composeapp.generated.resources.ic_home
import movieapp.composeapp.generated.resources.ic_settings
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun BottomBarWithAppContent() {
    val navigation = rememberNavigation()
    val navDestination = rememberNavDestination(initialDestination = MovieScreenDestination)

    val homeScreenSelected = navDestination in listOf(
        MovieScreenDestination, SeriesScreenDestination
    )

    val settingsScreenSelected = navDestination == SettingsScreenDestination

    val itemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
            unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
        ),
    )

    val borderColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

    AnimatedVisibility(
        visible = homeScreenSelected || settingsScreenSelected,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = MaterialTheme.padding.sixteen)
                .clip(MaterialTheme.shapes.large)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f))
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = MaterialTheme.shapes.large
                    ),
                color = Color.Transparent,
                tonalElevation = 0.dp,
            ) {
                Row(
                    modifier = Modifier.padding(vertical = MaterialTheme.padding.five),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    NoRippleNavItem(
                        selected = homeScreenSelected,
                        onClick = {
                            navigation.switchTo(HomeScreenSection)
                        },
                        icon = vectorResource(Res.drawable.ic_home),
                        label = stringResource(Res.string.home_screen),
                        colors = itemColors.navigationBarItemColors
                    )

                    NoRippleNavItem(
                        selected = settingsScreenSelected,
                        onClick = {
                            navigation.switchTo(SettingsSection)
                        },
                        icon = vectorResource(Res.drawable.ic_settings),
                        label = stringResource(Res.string.settings_screen_title),
                        colors = itemColors.navigationBarItemColors
                    )
                }
            }
        }
    }
}