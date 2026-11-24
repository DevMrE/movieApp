package com.kmp.movieapp.di

import com.kmp.movieapp.browse.di.searchModule
import com.kmp.movieapp.genre.di.genreModule
import com.kmp.movieapp.discover.di.discoverModule
import com.kmp.movieapp.movie.di.movieModule
import com.kmp.movieapp.overview.di.overviewModule
import com.kmp.movieapp.series.di.seriesModule
import com.kmp.movieapp.trending.di.trendingModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        discoverModule,
        trendingModule,
        movieModule,
        seriesModule,
        searchModule,
        overviewModule,
        genreModule
    )
}