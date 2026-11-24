package com.kmp.movieapp.browse.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.browse.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.browse.domain.usecase.SearchOrDiscoverUseCase
import com.kmp.movieapp.browse.presentation.SearchViewModel
import com.kmp.movieapp.browse.presentation.content.BrowseStartContent
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val searchModule = module {

    factory {
        SearchOrDiscoverUseCase(
            searchUseCase = get(),
            getDiscoverUseCase = get()
        )
    }

    factory {
        GetDiscoverUseCase(
            movieRepository = get(),
            seriesRepository = get()
        )
    }

    viewModel {
        SearchViewModel(
            genreRepository = get(),
            navigator = get(qualifier = navigatorQualifier<BrowseNavigation>()),
            searchOrDiscoverUseCase = get()
        )
    }

    // Navigations
    navigation<BrowseNavigation.InitialScreenRoute> {
        BrowseStartContent()
    }

    navigation<BrowseNavigation.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions(),
    ) { data ->
        val navigation = get<Navigator<BrowseNavigation>>(qualifier = navigatorQualifier<BrowseNavigation>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = { navigation.navigateBack() })
    }
}