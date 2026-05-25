package com.kmp.movieapp.home.di

import com.kmp.movieapp.content_detail.presentation.ContentDetailScreen
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.navigation.util.navigatorQualifier
import com.kmp.movieapp.home.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.home.presentation.content.HomeStartContent
import com.kmp.movieapp.overview_list.presentation.OverviewListScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {

    factory {
        GetHomeDataUseCase(
            getTrendingUseCase = get(),
            getPopularMoviesUseCase = get(),
            getPopularSeriesUseCase = get()
        )
    }

    viewModel {
        HomeScreenViewModel(
            getHomeDataUseCase = get(),
            navigator = get(navigatorQualifier<HomeNavigation>())
        )
    }

    navigation<HomeNavigation.InitialScreenRoute> {
        HomeStartContent()
    }

    navigation<HomeNavigation.SeeAllRoute> { data ->
        OverviewListScreen(data.mediaCategory)
    }

    navigation<HomeNavigation.ContentDetailRoute> { data ->
        val navigation =
            get<Navigator<HomeNavigation>>(qualifier = navigatorQualifier<HomeNavigation>())

        ContentDetailScreen(
            id = data.id,
            mediaCategory = data.mediaCategory,
            onBackClicked = {
                navigation.navigateBack()
            }
        )
    }
}