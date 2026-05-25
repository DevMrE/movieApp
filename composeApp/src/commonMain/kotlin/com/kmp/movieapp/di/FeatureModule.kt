package com.kmp.movieapp.di

import com.kmp.movieapp.browse.di.browseModule
import com.kmp.movieapp.discover.di.discoverModule
import com.kmp.movieapp.home.di.homeModule
import com.kmp.movieapp.movie.di.movieModule
import com.kmp.movieapp.overview_list.di.overviewListModule
import com.kmp.movieapp.series.di.seriesModule
import com.kmp.movieapp.trending.di.trendingModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        homeModule,
        trendingModule,
        movieModule,
        seriesModule,
        overviewListModule,
        discoverModule,
        browseModule
    )
}