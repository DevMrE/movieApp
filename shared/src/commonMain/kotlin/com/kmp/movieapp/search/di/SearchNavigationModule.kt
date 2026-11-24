package com.kmp.movieapp.search.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.search.presentation.content.SearchScreen
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val searchNavigationModule = module {
    // ------- Search Screen Navigation -------
    single<Navigator<SearchRoute>>(qualifier = navigatorQualifier<SearchRoute>()) {
        RegisterNavigationGraph(startDestination = SearchRoute.InitialScreenRoute)
    }

    navigation<SearchRoute.InitialScreenRoute> {
        SearchScreen()
    }

    navigation<SearchRoute.ContentDetailRoute>(
        metadata = NavigationScreenAnimation.bottomSheetTransitions(),
    ) { data ->
        val navigation = get<Navigator<SearchRoute>>(qualifier = navigatorQualifier<SearchRoute>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = { navigation.navigateBack() })
    }
}