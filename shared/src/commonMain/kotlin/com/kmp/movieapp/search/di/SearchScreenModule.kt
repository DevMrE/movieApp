package com.kmp.movieapp.search.di

import com.devmre.compose.navigation3.helper.navigatorQualifier
import com.kmp.movieapp.search.domain.usecase.GetSearchedMediaUseCase
import com.kmp.movieapp.search.domain.usecase.SearchOrDiscoverUseCase
import com.kmp.movieapp.search.presentation.SearchViewModel
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    includes(searchTextFieldModule)

    factory {
        SearchOrDiscoverUseCase(
            searchUseCase = get(),
            getSearchedMediaUseCase = get()
        )
    }

    factory {
        GetSearchedMediaUseCase(
            movieRepository = get(),
            seriesRepository = get()
        )
    }

    viewModel {
        SearchViewModel(
            genreRepository = get(),
            navigator = get(qualifier = navigatorQualifier<SearchRoute>()),
            searchOrDiscoverUseCase = get()
        )
    }
}