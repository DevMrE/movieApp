package com.kmp.movieapp.components.bottombar.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.components.bottombar.navigation.BottomBarRoute
import com.kmp.movieapp.components.topbar.util.TopBarConfig
import com.kmp.movieapp.components.topbar.util.TopBarMetadata
import com.kmp.movieapp.discover.presentation.navigation.DiscoverScreenGraph
import com.kmp.movieapp.profile.ProfileScreen
import com.kmp.movieapp.search.presentation.navigation.SearchScreenGraph
import com.kmp.movieapp.shared.Res
import com.kmp.movieapp.shared.bottom_bar_discover
import com.kmp.movieapp.shared.bottom_bar_profile
import com.kmp.movieapp.shared.bottom_bar_search
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val bottomBarNavigationModule = module {
    // ------- Bottom Bar -------
    single<Navigator<BottomBarRoute>>(qualifier = navigatorQualifier<BottomBarRoute>()) {
        RegisterNavigationGraph(startDestination = BottomBarRoute.DiscoverRoute)
    }

    navigation<BottomBarRoute.DiscoverRoute>(
        metadata = TopBarMetadata.config(TopBarConfig(title = Res.string.bottom_bar_discover))
    ) {
        DiscoverScreenGraph()
    }

    navigation<BottomBarRoute.SearchRoute>(
        metadata = TopBarMetadata.config(TopBarConfig(title = Res.string.bottom_bar_search))
    ) {
        SearchScreenGraph()
    }

    navigation<BottomBarRoute.Profile>(
        metadata = TopBarMetadata.config(TopBarConfig(title = Res.string.bottom_bar_profile))
    ) {
        ProfileScreen()
    }
}