package com.kmp.movieapp.app.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.movie.presentation.destination.MovieScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavSection
import com.kmp.navigation.compose.rememberNavigation
import com.kmp.series.presentation.destination.SeriesScreenSection
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.ic_home
import movieapp.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomBarWithAppContent(
    content: @Composable () -> Unit
) {
    val navigation = rememberNavigation()
    val section = rememberNavSection(initialSection = HomeScreenSection)

    val homeScreenSelected = section in listOf(
        MovieScreenSection, SeriesScreenSection,
        HomeScreenSection
    )

    val settingsScreenSelected = section == SettingsSection

    val itemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = MaterialTheme.colorScheme.primary,
        ),
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            thickness = MaterialTheme.size.bottomBarStrokeHeight,
            color = MaterialTheme.colorScheme.surfaceVariant
        )

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                item(
                    selected = homeScreenSelected,
                    onClick = {
                        navigation.switchTo(HomeScreenSection)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_home),
                            contentDescription = null,
                        )
                    },
                    colors = itemColors
                )

                item(
                    selected = settingsScreenSelected,
                    onClick = {
                        navigation.switchTo(SettingsSection)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_settings),
                            contentDescription = null,
                        )
                    },
                    colors = itemColors
                )
            },
            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContainerColor = MaterialTheme.colorScheme.background
            )
        ) {
            content()
        }
    }
}