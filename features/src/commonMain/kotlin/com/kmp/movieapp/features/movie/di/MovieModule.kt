package com.kmp.movieapp.features.movie.di

import com.kmp.movieapp.features.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.features.media_list.presentation.MediaListViewModel
import com.kmp.movieapp.features.movie.data.domain.repository.MovieRepository
import com.kmp.movieapp.features.movie.data.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.features.movie.data.domain.usecase.GetPopularMoviesUseCase
import com.kmp.movieapp.features.movie.data.domain.usecase.LoadNextMoviesForCategoryUseCase
import com.kmp.movieapp.features.movie.data.repository.MovieRepositoryImpl
import com.kmp.movieapp.features.movie.data.service.MovieApiService
import com.kmp.movieapp.features.movie.data.service.MovieApiServiceImpl
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val movieModule = module {
    single<MovieApiService> {
        MovieApiServiceImpl(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    factory<GetPopularMoviesUseCase> {
        GetPopularMoviesUseCase(get())
    }

    factory<LoadNextMoviesForCategoryUseCase> {
        LoadNextMoviesForCategoryUseCase(get(), get(), get())
    }

    factory { GetHomeDataUseCase(get(), get(), get()) }

    viewModelOf(::HomeScreenViewModel)

    viewModel { params ->
        MediaListViewModel(
            loadNextMoviesForCategoryUseCase = get(),
            homeCategory = params.get()
        )
    }
}