package com.kmp.movieapp.navigation.di

import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.AppNavigation
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.discover.presentation.DiscoverScreen
import com.kmp.movieapp.navigation.home.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.navigation.home.presentation.HomeScreen
import com.kmp.movieapp.navigation.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.navigation.util.NavigatorImpl
import com.kmp.movieapp.overview_list.presentation.OverviewListScreen
import com.kmp.movieapp.search.presentation.SearchContent
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val navigationModule = module {

    factory {
        GetHomeDataUseCase(
            getTrendingUseCase = get(),
            getPopularMoviesUseCase = get(),
            getPopularSeriesUseCase = get()
        )
    }

    viewModelOf(::HomeScreenViewModel)

    navigation<AppNavigation.Home> {
        HomeScreen()
    }

    navigation<AppNavigation.Browse> {
        DiscoverScreen()
    }

    navigation<HomeNavigation.SeeAllRoute> { data ->
        OverviewListScreen(data.mediaCategory)
    }

    navigation<HomeNavigation.ContentDetail> { data ->
        ContentDetailScreen(data.id, data.mediaCategory)
    }

    navigation<AppNavigation.Search> {
        SearchContent()
    }

    single<Navigator<Route>> {
        NavigatorImpl(startDestination = AppNavigation.Home)
    }
}