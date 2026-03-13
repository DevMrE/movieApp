package com.kmp.movieapp.movie.di

import com.kmp.movieapp.movie.data.repository.MovieRepositoryImpl
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.data.service.MovieServiceImpl
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.movie.domain.usecase.GetMoviesForCategoryUseCase
import com.kmp.movieapp.movie.domain.usecase.LoadNextMoviesForCategoryUseCase
import com.kmp.movieapp.movie.presentation.MovieScreenViewModel
import com.kmp.movieapp.movie.presentation.movie_list_category.MovieCategoryListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieModule = module {
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

    viewModelOf(::MovieScreenViewModel)

    viewModel { params ->
        MovieCategoryListViewModel(
            loadNextMoviesForCategoryUseCase = get(),
            movieCategory = params.get()
        )
    }
}