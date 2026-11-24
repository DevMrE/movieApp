package com.kmp.movieapp.discover.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.route.DiscoverRoute
import com.kmp.movieapp.discover.domain.GetDiscoverDataUseCase
import com.kmp.movieapp.discover.presentation.DiscoverScreen
import com.kmp.movieapp.discover.presentation.DiscoverScreenViewModel
import com.kmp.movieapp.overview.OverviewListScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val discoverModule = module {

    factory {
        GetDiscoverDataUseCase(
            trendingRepository = get(),
            movieRepository = get(),
            seriesRepository = get()
        )
    }

    viewModel {
        DiscoverScreenViewModel(
            getDiscoverDataUseCase = get(),
            navigator = get<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>())
        )
    }

    single<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>()) {
        RegisterNavigationGraph(startDestination = DiscoverRoute.InitialScreenRoute)
    }

    navigation<DiscoverRoute.InitialScreenRoute> {
        DiscoverScreen()
    }

    navigation<DiscoverRoute.SeeAllRoute>(
        metadata = NavigationScreenAnimation.slideSheetTransition()
    ) { data ->
        OverviewListScreen(data.mediaCategory)
    }

    navigation<DiscoverRoute.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions()
    ) { data ->
        val navigation = get<Navigator<DiscoverRoute>>(qualifier = navigatorQualifier<DiscoverRoute>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = {
                navigation.navigateBack()
            }
        )
    }
}