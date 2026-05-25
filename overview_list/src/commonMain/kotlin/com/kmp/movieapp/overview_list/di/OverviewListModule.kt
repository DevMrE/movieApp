package com.kmp.movieapp.overview_list.di

import com.kmp.movieapp.overview_list.domain.usecase.LoadMediaListForCategoryUseCase
import org.koin.dsl.module

val overviewListModule = module {

    factory {
        LoadMediaListForCategoryUseCase(
            popularSeries = get(),
            popularMoviesUseCase = get()
        )
    }
}