package com.kmp.series.di

import com.kmp.series.data.repository.SeriesRepositoryImpl
import com.kmp.series.data.service.SeriesService
import com.kmp.series.data.service.SeriesServiceImpl
import com.kmp.series.domain.repository.SeriesRepository
import org.koin.dsl.module

val seriesModule = module {
    single<SeriesService> { SeriesServiceImpl(get()) }

    single<SeriesRepository> { SeriesRepositoryImpl(get()) }
}