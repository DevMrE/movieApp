package com.kmp.movieapp.search.di

import com.kmp.movieapp.search.data.repository.SearchRepoImpl
import com.kmp.movieapp.search.data.service.SearchApiService
import com.kmp.movieapp.search.data.service.SearchApiServiceImpl
import com.kmp.movieapp.search.domain.repository.SearchRepository
import com.kmp.movieapp.search.domain.usecase.SearchUseCase
import com.kmp.movieapp.search.presentation.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    single<SearchApiService> {
        SearchApiServiceImpl(get())
    }

    single<SearchRepository> {
        SearchRepoImpl(get())
    }

    factory<SearchUseCase> {
        SearchUseCase(get())
    }

    viewModelOf(::SearchViewModel)
}