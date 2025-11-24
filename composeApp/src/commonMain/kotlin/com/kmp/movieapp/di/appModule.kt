package com.kmp.movieapp.di

import com.kmp.movieapp.homescreen.HomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {


    viewModelOf(::HomeScreenViewModel)
}