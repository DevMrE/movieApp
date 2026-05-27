package com.kmp.movieapp.browse.di

import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.browse.BrowseViewModel
import com.kmp.movieapp.browse.content.BrowseStartContent
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.navigation.util.navigatorQualifier
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val browseModule = module {
    viewModel {
        BrowseViewModel(
            getDiscoverUseCase = get(),
            navigator = get(qualifier = navigatorQualifier<BrowseNavigation>()),
            genreRepository = get()
        )
    }

    // Screens
    navigation<BrowseNavigation.InitialScreenRoute> {
        BrowseStartContent()
    }

    navigation<BrowseNavigation.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions()
    ) { data ->
        val navigation =
            get<Navigator<BrowseNavigation>>(qualifier = navigatorQualifier<BrowseNavigation>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = { navigation.navigateBack() })
    }
}