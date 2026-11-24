package com.kmp.movieapp.discover.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.discover.domain.GetDiscoverDataUseCase
import com.kmp.movieapp.discover.presentation.DiscoverScreenViewModel
import com.kmp.movieapp.discover.presentation.navigation.DiscoverRoute
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

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
}