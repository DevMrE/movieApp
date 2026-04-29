package com.kmp.movieapp.trending.di

import com.kmp.movieapp.trending.data.repository.TrendingRepositoryImpl
import com.kmp.movieapp.trending.data.service.TrendingApiService
import com.kmp.movieapp.trending.data.service.TrendingApiServiceImpl
import com.kmp.movieapp.trending.domain.GetTrendingUseCase
import com.kmp.movieapp.trending.domain.repository.TrendingRepository
import org.koin.dsl.module

val trendingModule = module {
    single<TrendingApiService> {
        TrendingApiServiceImpl(get())
    }

    single<TrendingRepository> {
        TrendingRepositoryImpl(get())
    }

    factory {
        GetTrendingUseCase(get())
    }
}