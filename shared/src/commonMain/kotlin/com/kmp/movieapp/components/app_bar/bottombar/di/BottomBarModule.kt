package com.kmp.movieapp.components.app_bar.bottombar.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.components.app_bar.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.discover.presentation.navigation.DiscoverScreenGraph
import com.kmp.movieapp.more.MoreScreen
import com.kmp.movieapp.search.presentation.navigation.SearchScreenGraph
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val bottomBarNavigationModule = module {
    // ------- Bottom Bar -------
    single<Navigator<BottomBarRoute>>(qualifier = navigatorQualifier<BottomBarRoute>()) {
        RegisterNavigationGraph(startDestination = BottomBarRoute.DiscoverRoute)
    }

    navigation<BottomBarRoute.DiscoverRoute> {
        DiscoverScreenGraph()
    }

    navigation<BottomBarRoute.SearchRoute> {
        SearchScreenGraph()
    }

    navigation<BottomBarRoute.Profile> {
        MoreScreen()
    }
}