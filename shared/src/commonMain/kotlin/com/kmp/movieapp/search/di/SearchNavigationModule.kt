package com.kmp.movieapp.search.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.route.SearchScreenRoute
import com.kmp.movieapp.search.presentation.content.SearchScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val searchNavigationModule = module {
    // ------- Search Screen Navigation -------
    single<Navigator<SearchScreenRoute>>(qualifier = navigatorQualifier<SearchScreenRoute>()) {
        RegisterNavigationGraph(startDestination = SearchScreenRoute.InitialScreenRoute)
    }

    navigation<SearchScreenRoute.InitialScreenRoute> {
        SearchScreen()
    }

    navigation<SearchScreenRoute.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions(),
    ) { data ->
        val navigation = get<Navigator<SearchScreenRoute>>(qualifier = navigatorQualifier<SearchScreenRoute>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = { navigation.navigateBack() })
    }
}