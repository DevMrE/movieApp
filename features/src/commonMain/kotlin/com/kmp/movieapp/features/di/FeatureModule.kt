package com.kmp.movieapp.features.di

import com.kmp.movieapp.movie.di.movieModule
import com.kmp.movieapp.series.di.seriesModule
import com.kmp.movieapp.trending.di.trendingModule
import org.koin.dsl.module

val featureModule = module {
    includes(trendingModule, movieModule, seriesModule)
}