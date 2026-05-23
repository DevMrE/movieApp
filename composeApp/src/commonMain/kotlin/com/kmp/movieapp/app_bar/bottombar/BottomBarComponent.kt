package com.kmp.movieapp.app_bar.bottombar

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
import com.kmp.movieapp.app_bar.bottombar.component.NavItem
import com.kmp.movieapp.app_bar.bottombar.model.BottomBarItem
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.discover_media_title
import com.kmp.movieapp.composeApp.home_screen
import com.kmp.movieapp.composeApp.ic_fire
import com.kmp.movieapp.composeApp.ic_home
import com.kmp.movieapp.composeApp.ic_more
import com.kmp.movieapp.composeApp.more
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

private val bottomBarItemList = listOf(
    BottomBarItem(
        icon = Res.drawable.ic_home,
        label = Res.string.home_screen,
        navDestination = AppNavigation.Home
    ),
    BottomBarItem(
        icon = Res.drawable.ic_fire,
        label = Res.string.discover_media_title,
        navDestination = AppNavigation.Browse
    ),
    BottomBarItem(
        icon = Res.drawable.ic_more,
        label = Res.string.more,
        navDestination = AppNavigation.More
    )
)

@Composable
internal fun BottomBarComponent() {
    val navigator = koinInject<Navigator<Route>>()
    val currentScreen = navigator.backStack.lastOrNull()

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
        visible = currentScreen is AppNavigation,
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
                    selected = currentScreen == navItem.navDestination,
                    onClick = {
                        navigator.switchTo(navItem.navDestination)
                    },
                    icon = vectorResource(navItem.icon),
                    labelResource = navItem.label,
                    colors = itemColors.navigationBarItemColors
                )
            }
        }
    }
}