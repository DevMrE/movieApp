package com.kmp.movieapp.series.di

import com.kmp.movieapp.series.data.repository.SeriesRepositoryImpl
import com.kmp.movieapp.series.data.service.api.SeriesApiService
import com.kmp.movieapp.series.data.service.api.SeriesApiServiceImpl
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import com.kmp.movieapp.series.domain.usecase.GetPopularSeriesUseCase
import org.koin.dsl.module

val seriesModule = module {
    single<SeriesApiService> {
        SeriesApiServiceImpl(
            get()
        )
    }

    single<SeriesRepository> {
        SeriesRepositoryImpl(
            get()
        )
    }

    factory {
        GetPopularSeriesUseCase(get())
    }
}