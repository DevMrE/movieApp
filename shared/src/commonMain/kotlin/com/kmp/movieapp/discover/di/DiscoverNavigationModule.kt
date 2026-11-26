package com.kmp.movieapp.discover.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.koinNavigation
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.components.topbar.mapper.titleRes
import com.kmp.movieapp.components.topbar.util.TopBarConfig
import com.kmp.movieapp.components.topbar.util.TopBarMetadata
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.discover.presentation.DiscoverScreen
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import com.kmp.movieapp.overview.presentation.OverviewListScreen
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.bottom_bar_discover
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val discoverNavigationModule = module {
    // ------- Discover Screen Navigation -------
    single<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>()) {
        RegisterNavigationGraph(startDestination = DiscoverRoute.InitialScreenRoute)
    }

    navigation<DiscoverRoute.InitialScreenRoute>(
        metadata = TopBarMetadata.config(TopBarConfig(title = Res.string.bottom_bar_discover))
    ) {
        DiscoverScreen()
    }

    navigation<DiscoverRoute.SeeAllRoute>(
        metadata = NavigationScreenAnimation.slideSheetTransition() +
                TopBarMetadata.config<DiscoverRoute.SeeAllRoute> { route ->
                    val navigator =
                        get<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>())

                    TopBarConfig(
                        title = route.mediaCategory.titleRes(),
                        onBackClick = { navigator.navigateBack() },
                    )
                }
    ) { data ->
        val navigator = koinNavigation<DiscoverRoute>()

        OverviewListScreen(
            mediaCategory = data.mediaCategory,
            navigator = navigator
        )
    }

    navigation<DiscoverRoute.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions()
    ) { data ->
        val navigation =
            get<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = {
                navigation.navigateBack()
            }
        )
    }
}