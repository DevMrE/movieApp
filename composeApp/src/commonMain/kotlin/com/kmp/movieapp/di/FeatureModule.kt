package com.kmp.movieapp.di

import com.kmp.movieapp.movie.di.movieModule
import com.kmp.movieapp.overview_list.di.overviewListModule
import com.kmp.movieapp.series.di.seriesModule
import com.kmp.movieapp.trending.di.trendingModule
import org.koin.dsl.module

val featureModule = module {
    includes(trendingModule, movieModule, seriesModule, overviewListModule)
}