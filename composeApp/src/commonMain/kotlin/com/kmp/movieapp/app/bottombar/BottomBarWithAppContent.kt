package com.kmp.movieapp.app.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmp.movieapp.app.bottombar.component.NavItem
import com.kmp.movieapp.app.bottombar.model.BottomBarItem
import com.kmp.movieapp.app.navigation.destination.BottomBarTabs
import com.kmp.movieapp.app.navigation.destination.DiscoverMoviesDestination
import com.kmp.movieapp.core.presentation.material.padding
import com.kmp.movieapp.homescreen.destination.HomeDestination
import com.kmp.movieapp.settings.destination.SettingsDestination
import com.kmp.navigation.compose.rememberActiveTabIn
import com.kmp.navigation.compose.rememberIsTabsActive
import com.kmp.navigation.compose.rememberNavigation
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.home_screen
import movieapp.composeapp.generated.resources.ic_home
import movieapp.composeapp.generated.resources.ic_movie
import movieapp.composeapp.generated.resources.ic_settings
import movieapp.composeapp.generated.resources.movie_tab
import movieapp.composeapp.generated.resources.settings_screen_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

private val bottomBarItemList = listOf(
    BottomBarItem(
        icon = Res.drawable.ic_home,
        label = Res.string.home_screen,
        navDestination = HomeDestination
    ),
    BottomBarItem(
        icon = Res.drawable.ic_movie,
        label = Res.string.movie_tab,
        navDestination = DiscoverMoviesDestination
    ),
    BottomBarItem(
        icon = Res.drawable.ic_settings,
        label = Res.string.settings_screen_title,
        navDestination = SettingsDestination
    )
)

@Composable
internal fun BottomBarWithAppContent() {
    val navigation = rememberNavigation()
    val navDestination = rememberActiveTabIn<BottomBarTabs>()
    val isActive = rememberIsTabsActive<BottomBarTabs>()

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
        modifier = Modifier.background(Color.Transparent),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.sixteen)
                .navigationBarsPadding()
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = MaterialTheme.shapes.large
                )
                .clip(MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            bottomBarItemList.forEach { navItem ->
                NavItem(
                    selected = navDestination == navItem.navDestination,
                    onClick = {
                        navigation.navigateTo(navItem.navDestination)
                    },
                    icon = vectorResource(navItem.icon),
                    label = stringResource(navItem.label),
                    colors = itemColors.navigationBarItemColors
                )
            }
        }
    }
}