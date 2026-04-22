package com.kmp.movieapp.features.series.di

import com.kmp.movieapp.features.series.data.service.api.SeriesApiService
import com.kmp.movieapp.features.series.data.service.api.SeriesApiServiceImpl
import org.koin.dsl.module

internal val seriesModule = module {
    single<SeriesApiService> { SeriesApiServiceImpl(get()) }
}