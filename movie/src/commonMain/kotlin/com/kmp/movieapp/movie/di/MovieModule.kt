package com.kmp.movieapp.movie.di

import com.kmp.movieapp.movie.data.repository.MovieRepositoryImpl
import com.kmp.movieapp.movie.data.service.MovieApiService
import com.kmp.movieapp.movie.data.service.MovieApiServiceImpl
import com.kmp.movieapp.movie.domain.repository.MovieRepository
import org.koin.dsl.module

val movieModule = module {
    single<MovieApiService> {
        MovieApiServiceImpl(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
}