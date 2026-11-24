package com.kmp.movieapp.overview.di

import com.devmre.compose.navigation3.helper.Navigator
import com.devmre.compose.navigation3.helper.RegisterNavigationGraph
import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.overview.OverviewListViewModel
import com.kmp.movieapp.overview.domain.usecase.LoadOverviewMediaList
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val overviewModule = module {

    factory {
        LoadOverviewMediaList(
            movieRepository = get(),
            seriesRepository = get()
        )
    }

    viewModelOf(::OverviewListViewModel)

    single<Navigator<BrowseNavigation>>(qualifier = navigatorQualifier<BrowseNavigation>()) {
        RegisterNavigationGraph(startDestination = BrowseNavigation.InitialScreenRoute)
    }
}