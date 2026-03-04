package com.kmp.movieapp.app.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
import co.touchlab.kermit.Logger
import com.kmp.movieapp.app.bottombar.component.NoRippleNavItem
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.navigation.destination.DiscoverMoviesDestination
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberIsTabsActive
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun BottomBarWithAppContent() {
    val navigation = rememberNavigation()
    val navDestination = rememberActiveTabIn<BottomBarTabs>()
    val isActive = rememberIsTabsActive<BottomBarTabs>()

    Logger.i(tag = "KmpNavigation", messageString = "BottomBarWithAppContent: $navDestination")
    Logger.i(tag = "KmpNavigation", messageString = "BottomBarWithAppContent isActive: $isActive")


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
        visible = isActive,
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
                        selected = navDestination == HomeDestination,
                        onClick = {
                            navigation.navigateTo(HomeDestination)
                        },
                        icon = vectorResource(Res.drawable.ic_home),
                        label = stringResource(Res.string.home_screen),
                        colors = itemColors.navigationBarItemColors
                    )

                    NoRippleNavItem(
                        selected = navDestination == SettingsDestination,
                        onClick = {
                            navigation.navigateTo(SettingsDestination)
                        },
                        icon = vectorResource(Res.drawable.ic_settings),
                        label = stringResource(Res.string.settings_screen_title),
                        colors = itemColors.navigationBarItemColors
                    )

                    NoRippleNavItem(
                        selected = navDestination == DiscoverMoviesDestination,
                        onClick = {
                            navigation.navigateTo(DiscoverMoviesDestination)
                        },
                        icon = vectorResource(Res.drawable.ic_settings),
                        label = "Movies",
                        colors = itemColors.navigationBarItemColors
                    )
                }
            }
        }
    }
}