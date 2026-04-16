package com.kmp.movieapp.features.home.di

import com.kmp.movieapp.features.home.data.repository.MovieRepositoryImpl
import com.kmp.movieapp.features.home.data.service.MovieService
import com.kmp.movieapp.features.home.data.service.MovieServiceImpl
import com.kmp.movieapp.features.home.domain.repository.MovieRepository
import com.kmp.movieapp.features.home.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.features.home.domain.usecase.LoadNextMoviesForCategoryUseCase
import com.kmp.movieapp.features.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.features.home.presentation.home_list_category.MovieCategoryListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featuresModule = module {
    single<MovieService> {
        MovieServiceImpl(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    factory<GetMoviesForCategoryUseCase> {
        GetMoviesForCategoryUseCase(get())
    }

    factory<LoadNextMoviesForCategoryUseCase> {
        LoadNextMoviesForCategoryUseCase(get())
    }

    viewModelOf(::HomeScreenViewModel)

    viewModel { params ->
        MovieCategoryListViewModel(
            loadNextMoviesForCategoryUseCase = get(),
            homeCategory = params.get()
        )
    }
}