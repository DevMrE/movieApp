package com.kmp.movieapp.components.app_bar.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.app_bar.bottombar.model.BottomBarItem
import com.kmp.movieapp.components.app_bar.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.bottom_bar_discover
import com.kmp.movieapp.shared.bottom_bar_profile
import com.kmp.movieapp.shared.bottom_bar_search
import com.kmp.movieapp.shared.ic_movie
import com.kmp.movieapp.shared.ic_profile
import com.kmp.movieapp.shared.ic_search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

private val bottomBarItemList = listOf(
    BottomBarItem(
        icon = Res.drawable.ic_movie,
        label = Res.string.bottom_bar_discover,
        navDestination = BottomBarRoute.DiscoverRoute
    ),
    BottomBarItem(
        icon = Res.drawable.ic_search,
        label = Res.string.bottom_bar_search,
        navDestination = BottomBarRoute.SearchRoute
    ),
    BottomBarItem(
        icon = Res.drawable.ic_profile,
        label = Res.string.bottom_bar_profile,
        navDestination = BottomBarRoute.Profile
    )
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun BottomBarComponent() {
    val bottomBarNavigation = koinNavigation<BottomBarRoute>()
    val discoverNavigation = koinNavigation<DiscoverRoute>()
    val searchRoute = koinNavigation<SearchRoute>()

    val currentBottomBarRoute = bottomBarNavigation.backStack.lastOrNull()
    val currentDiscoverRoute = discoverNavigation.backStack.lastOrNull()
    val currentSearchRoute = searchRoute.backStack.lastOrNull()

    val itemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.primaryFixedDim,
            unselectedTextColor = MaterialTheme.colorScheme.primaryFixedDim,
        ),
    )

    val borderColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

    val showBottomBar =
        currentDiscoverRoute is DiscoverRoute.InitialScreenRoute && currentSearchRoute is SearchRoute.InitialScreenRoute

    AnimatedVisibility(
        visible = showBottomBar,
        modifier = Modifier.background(Color.Transparent),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.padding.sixteen)
                .navigationBarsPadding()
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = MaterialTheme.shapes.extraExtraLarge
                )
                .clip(MaterialTheme.shapes.extraExtraLarge)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.98f))
                .clickable(false, onClick = {}),
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.94f),
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {

            bottomBarItemList.forEach { navItem ->
                NavigationBarItem(
                    selected = currentBottomBarRoute == navItem.navDestination,
                    onClick = {
                        bottomBarNavigation.switchTo(navItem.navDestination)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(navItem.icon),
                            contentDescription = null,
                        )
                    },
                    label = {
                        navItem.label?.let {
                            Text(
                                text = stringResource(it),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                )
            }
        }
    }
}