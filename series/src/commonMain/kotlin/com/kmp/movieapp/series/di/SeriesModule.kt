package com.kmp.movieapp.series.di

import com.kmp.movieapp.series.data.repository.SeriesRepositoryImpl
import com.kmp.movieapp.series.data.service.SeriesService
import com.kmp.movieapp.series.data.service.SeriesServiceImpl
import com.kmp.movieapp.series.domain.repository.SeriesRepository
import com.kmp.movieapp.series.domain.usecase.GetPopularSeriesUseCase
import org.koin.dsl.module

val seriesModule = module {
    single<SeriesService> {
        SeriesServiceImpl(
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