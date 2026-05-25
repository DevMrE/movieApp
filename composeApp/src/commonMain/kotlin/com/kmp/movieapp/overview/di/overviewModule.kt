package com.kmp.movieapp.overview.di

import com.kmp.movieapp.overview.OverviewListViewModel
import com.kmp.movieapp.overview_list.di.overviewListModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val overviewModule = module {
    includes(overviewListModule)
    viewModelOf(::OverviewListViewModel)
}