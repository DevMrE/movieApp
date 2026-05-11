package com.kmp.movieapp.overview_list.di

import com.kmp.movieapp.overview_list.domain.usecase.LoadMediaListForCategoryUseCase
import com.kmp.movieapp.overview_list.presentation.OverviewListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val overviewListModule = module {

    factory {
        LoadMediaListForCategoryUseCase(
            get(),
            get()
        )
    }

    viewModelOf(::OverviewListViewModel)
}