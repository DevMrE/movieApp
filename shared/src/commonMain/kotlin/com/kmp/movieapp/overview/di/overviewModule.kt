package com.kmp.movieapp.overview.di

import com.kmp.movieapp.overview.OverviewListViewModel
import com.kmp.movieapp.overview.domain.usecase.LoadOverviewMediaList
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val overviewModule = module {

    factory {
        LoadOverviewMediaList(
            movieRepository = get(),
            seriesRepository = get()
        )
    }

    viewModelOf(::OverviewListViewModel)
}