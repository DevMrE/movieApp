package com.kmp.movieapp.features.di

import com.kmp.movieapp.features.home.di.homeModule
import com.kmp.movieapp.features.trending.di.trendingModule
import com.kmp.movieapp.movie.di.movieModule
import com.kmp.movieapp.series.di.seriesModule
import org.koin.dsl.module

val featureModule = module {
    includes(homeModule, trendingModule, movieModule, seriesModule)
}