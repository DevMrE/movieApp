package com.kmp.movieapp.features.di

import com.kmp.movieapp.features.movie.di.movieModule
import com.kmp.movieapp.features.series.di.seriesModule
import com.kmp.movieapp.features.trending.di.trendingModule
import org.koin.dsl.module

val featureModule = module {
    includes(trendingModule, movieModule, seriesModule)
}