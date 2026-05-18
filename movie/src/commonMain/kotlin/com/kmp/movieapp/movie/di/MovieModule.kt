package com.kmp.movieapp.movie.di

import com.kmp.movieapp.movie.data.repository.MovieRepositoryImpl
import com.kmp.movieapp.movie.data.service.MovieService
import com.kmp.movieapp.movie.data.service.MovieServiceImpl
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import com.kmp.movieapp.movie.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val movieModule = module {
    single<MovieService> {
        MovieServiceImpl(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    factory<GetPopularMoviesUseCase> {
        GetPopularMoviesUseCase(get())
    }
}