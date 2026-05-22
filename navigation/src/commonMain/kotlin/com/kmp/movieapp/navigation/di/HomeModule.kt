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
import com.kmp.movieapp.overview_list.presentation.MediaListComponent
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {

    factory {
        GetHomeDataUseCase(get(), get(), get())
    }

    viewModelOf(::HomeScreenViewModel)

    //scope<AppNavigation> {
    navigation<AppNavigation.Home> {
        HomeScreen()
    }

    navigation<AppNavigation.Browse> {
        DiscoverScreen()
    }

    navigation<HomeNavigation.SeeAllRoute> { data ->
        MediaListComponent(data.mediaCategory)
    }

    navigation<HomeNavigation.ContentDetail> { data ->
        ContentDetailScreen(data.id, data.mediaCategory)
    }
    //}

    single<Navigator<Route>> {
        NavigatorImpl(startDestination = AppNavigation.Home)
    }
}