package com.kmp.movieapp.components.app_bar.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.app_bar.bottombar.model.BottomBarItem
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.navigation.route.DiscoverRoute
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.bottom_bar_discover
import com.kmp.movieapp.shared.bottom_bar_more
import com.kmp.movieapp.shared.bottom_bar_search
import com.kmp.movieapp.shared.ic_more
import com.kmp.movieapp.shared.ic_movie
import com.kmp.movieapp.shared.ic_search
import org.jetbrains.compose.resources.vectorResource

private val bottomBarItemList = listOf(
    BottomBarItem(
        icon = Res.drawable.ic_movie,
        label = Res.string.bottom_bar_discover,
        navDestination = AppNavigation.DiscoverRoute
    ),
    BottomBarItem(
        icon = Res.drawable.ic_search,
        label = Res.string.bottom_bar_search,
        navDestination = AppNavigation.SearchRoute
    ),
    BottomBarItem(
        icon = Res.drawable.ic_more,
        label = Res.string.bottom_bar_more,
        navDestination = AppNavigation.MoreRoute
    )
)

@Composable
internal fun BottomBarComponent() {
    val appNavigator = koinNavigation<AppNavigation>()
    val homeNavigator = koinNavigation<DiscoverRoute>()
    val browseNavigation = koinNavigation<BrowseNavigation>()

    val currentAppRoute = appNavigator.backStack.lastOrNull()
    val currentHomeRoute = homeNavigator.backStack.lastOrNull()
    val currentDiscoverRoute = browseNavigation.backStack.lastOrNull()

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

    val showBottomBar = currentHomeRoute is DiscoverRoute.InitialScreenRoute && currentDiscoverRoute is BrowseNavigation.InitialScreenRoute

    AnimatedVisibility(
        visible = showBottomBar,
        modifier = Modifier.background(Color.Transparent),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(false, onClick = {})
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
                    selected = currentAppRoute == navItem.navDestination,
                    onClick = {
                        appNavigator.switchTo(navItem.navDestination)
                    },
                    icon = vectorResource(navItem.icon),
                    labelResource = navItem.label,
                    colors = itemColors.navigationBarItemColors,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}