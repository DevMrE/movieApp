package com.kmp.movieapp.di

import com.kmp.movieapp.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val moreScreenModule = module {
    viewModelOf(::ProfileViewModel)
}