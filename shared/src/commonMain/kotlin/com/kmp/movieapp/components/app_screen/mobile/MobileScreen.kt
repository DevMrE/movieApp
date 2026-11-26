package com.kmp.movieapp.components.app_screen.mobile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.NavDisplay
import com.devmre.compose.navigation3.helper.koinNavigation
import com.kmp.movieapp.components.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.components.topbar.component.TopAppBarComponent
import com.kmp.movieapp.components.topbar.util.TopBarConfig
import com.kmp.movieapp.components.topbar.util.rememberTopBarConfig
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.app_name
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(
    KoinExperimentalAPI::class,
)
@Composable
fun MobileScreen() {
    val koin = getKoin()
    val navigator = koinNavigation<BottomBarRoute>()
    val entryProvider = koinEntryProvider<BottomBarRoute>()

    val discoverConfig = rememberTopBarConfig(
        navigator = koinNavigation<DiscoverRoute>(),
        entryProvider = koinEntryProvider(),
        koin = koin
    )

    val searchConfig = rememberTopBarConfig(
        navigator = koinNavigation<SearchRoute>(),
        entryProvider = koinEntryProvider(),
        koin = koin
    )

    val profileConfig = rememberTopBarConfig(
        navigator = koinNavigation<BottomBarRoute>(),
        entryProvider = koinEntryProvider(),
        koin = koin
    )

    val topBarConfig = when (navigator.backStack.lastOrNull()) {
        is BottomBarRoute.DiscoverRoute -> discoverConfig
        is BottomBarRoute.SearchRoute -> searchConfig
        is BottomBarRoute.Profile -> profileConfig
        else -> TopBarConfig()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            topBarConfig.title?.let {
                TopAppBarComponent(
                    title = stringResource(Res.string.app_name),
                    onBackClick = topBarConfig.onBackClick
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val layoutDir = LocalLayoutDirection.current
        val paddingWithoutBottom = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDir),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDir),
            bottom = 0.dp
        )

        NavDisplay(
            backStack = navigator.backStack,
            entryProvider = entryProvider,
            modifier = Modifier.padding(paddingWithoutBottom)
        )
    }
}