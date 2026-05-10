package com.kmp.movieapp.homescreen.di

import com.kmp.movieapp.homescreen.domain.usecase.GetHomeDataUseCase
import com.kmp.movieapp.homescreen.presentation.HomeScreenViewModel
import com.kmp.movieapp.media_list.presentation.MediaListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {

    factory {
        GetHomeDataUseCase(get(), get(), get())
    }

    viewModelOf(::HomeScreenViewModel)

    viewModelOf(::MediaListViewModel)
}