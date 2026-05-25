package com.kmp.movieapp.discover.di

import com.kmp.movieapp.discover.data.repository.DiscoverRepositoryImpl
import com.kmp.movieapp.discover.data.service.DiscoverService
import com.kmp.movieapp.discover.data.service.DiscoverServiceImpl
import com.kmp.movieapp.discover.domain.repository.DiscoverRepository
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module

@OptIn(KoinExperimentalAPI::class)
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
}