package com.kmp.movieapp.features.trending.di

import com.kmp.movieapp.features.trending.data.repository.TrendingRepositoryImpl
import com.kmp.movieapp.features.trending.data.service.api.TrendingApiService
import com.kmp.movieapp.features.trending.data.service.api.TrendingApiServiceImpl
import com.kmp.movieapp.features.trending.domain.GetTrendingUseCase
import com.kmp.movieapp.features.trending.domain.repository.TrendingRepository
import org.koin.dsl.module

internal val trendingModule = module {
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