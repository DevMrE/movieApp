package com.kmp.movieapp.components.bottombar

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.bottombar.model.BottomBarItem
import com.kmp.movieapp.components.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.core.ui.material.padding
import com.kmp.movieapp.core.ui.theme.AppTheme
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

@Composable
internal fun BottomBarComponent() {
    val bottomBarNavigation = koinNavigation<BottomBarRoute>()
    val discoverNavigation = koinNavigation<DiscoverRoute>()
    val searchRoute = koinNavigation<SearchRoute>()

    val currentBottomBarRoute = bottomBarNavigation.backStack.lastOrNull()
    val currentDiscoverRoute = discoverNavigation.backStack.lastOrNull()
    val currentSearchRoute = searchRoute.backStack.lastOrNull()

    val showBottomBar =
        currentDiscoverRoute is DiscoverRoute.InitialScreenRoute && currentSearchRoute is SearchRoute.InitialScreenRoute

    BottomBarContent(
        showBottomBar = showBottomBar,
        currentBottomBarRoute = currentBottomBarRoute,
        onBottomBarClicked = { route ->
            bottomBarNavigation.switchTo(route)
        }
    )

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomBarContent(
    showBottomBar: Boolean,
    currentBottomBarRoute: BottomBarRoute?,
    onBottomBarClicked: (BottomBarRoute) -> Unit
) {

    val borderColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

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
                //.background(MaterialTheme.colorScheme.background.copy(alpha = 0.98f))
                .clickable(false, onClick = {}),
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.94f),
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {

            bottomBarItemList.forEach { navItem ->
                NavigationBarItem(
                    selected = currentBottomBarRoute == navItem.navDestination,
                    onClick = {
                        onBottomBarClicked(navItem.navDestination)
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

@PreviewLightDark
@Composable
private fun BottomBarPreview() {
    AppTheme {
        Scaffold(
            bottomBar = {
                BottomBarContent(
                    showBottomBar = true,
                    currentBottomBarRoute = BottomBarRoute.DiscoverRoute
                ) {}
            }
        ) {}
    }
}