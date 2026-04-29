package com.kmp.movieapp.features.home.di

import com.kmp.movieapp.features.home.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.features.home.presentation.HomeScreenViewModel
import com.kmp.movieapp.features.media_list.presentation.MediaListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {

    factory {
        GetHomeDataUseCase(get(), get(), get())
    }

    viewModelOf(::HomeScreenViewModel)

    viewModelOf(::MediaListViewModel)
}