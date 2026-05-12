package com.kmp.movieapp.discover.di

import com.kmp.movieapp.discover.data.repository.DiscoverRepositoryImpl
import com.kmp.movieapp.discover.data.service.DiscoverService
import com.kmp.movieapp.discover.data.service.DiscoverServiceImpl
import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.discover.presentation.DiscoverViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val discoverModule = module {

    single<DiscoverService> {
        DiscoverServiceImpl(get())
    }

    single<DiscoverRepository> {
        DiscoverRepositoryImpl(get())
    }

    factory {
        GetDiscoverUseCase(get())
    }

    viewModelOf(::DiscoverViewModel)
}