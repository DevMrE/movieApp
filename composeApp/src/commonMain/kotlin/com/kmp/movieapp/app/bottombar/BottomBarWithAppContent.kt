package com.kmp.movieapp.app.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.kmp.movieapp.core.presentation.material.size
import com.kmp.movieapp.homescreen.destination.HomeScreenSection
import com.kmp.movieapp.settings.destination.SettingsSection
import com.kmp.navigation.compose.rememberNavSection
import com.kmp.navigation.compose.rememberNavigation
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
                    selected = section == HomeScreenSection,
                    onClick = {
                        navigation.switchTo(HomeScreenSection)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_home),
                            contentDescription = null,
                        )
                    },
                )

                item(
                    selected = section == SettingsSection,
                    onClick = {
                        navigation.switchTo(SettingsSection)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_settings),
                            contentDescription = null,
                        )
                    },
                )
            },
            layoutType = NavigationSuiteType.ShortNavigationBarMedium,
            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContainerColor = MaterialTheme.colorScheme.background,
                shortNavigationBarContainerColor = MaterialTheme.colorScheme.background
            ),
        ) {
            content()
        }
    }
}