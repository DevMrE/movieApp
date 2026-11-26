package com.kmp.movieapp.search.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.animation.screen_animation.NavigationScreenAnimation
import com.kmp.movieapp.components.topbar.util.TopBarConfig
import com.kmp.movieapp.components.topbar.util.TopBarMetadata
import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.search.presentation.content.SearchScreen
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.search_title
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val searchNavigationModule = module {
    // ------- Search Screen Navigation -------
    single<Navigator<SearchRoute>>(qualifier = navigatorQualifier<SearchRoute>()) {
        RegisterNavigationGraph(startDestination = SearchRoute.InitialScreenRoute)
    }

    navigation<SearchRoute.InitialScreenRoute>(
        metadata = TopBarMetadata.config(TopBarConfig(title = Res.string.search_title))
    ) {
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