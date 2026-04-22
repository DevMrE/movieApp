package com.kmp.movieapp.features.series.di

import com.kmp.movieapp.features.series.data.repository.SeriesRepositoryImpl
import com.kmp.movieapp.features.series.data.service.api.SeriesApiService
import com.kmp.movieapp.features.series.data.service.api.SeriesApiServiceImpl
import com.kmp.movieapp.features.series.domain.repository.SeriesRepository
import com.kmp.movieapp.features.series.domain.usecase.GetPopularSeriesUseCase
import org.koin.dsl.module

internal val seriesModule = module {
    single<SeriesApiService> { SeriesApiServiceImpl(get()) }

    single<SeriesRepository> { SeriesRepositoryImpl(get()) }

    factory {
        GetPopularSeriesUseCase(get())
    }
}