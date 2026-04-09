package com.kmp.series.di

import com.kmp.series.data.service.SeriesService
import com.kmp.series.data.service.SeriesServiceImpl
import org.koin.dsl.module

val seriesModule = module {
    single<SeriesService> { SeriesServiceImpl(get()) }
}