package com.kmp.movieapp.search.di

import com.kmp.movieapp.search.data.repository.SearchRepoImpl
import com.kmp.movieapp.search.data.service.SearchApiService
import com.kmp.movieapp.search.data.service.SearchApiServiceImpl
import com.kmp.movieapp.search.domain.repository.SearchRepository
import com.kmp.movieapp.search.domain.usecase.SearchUseCase
import org.koin.dsl.module

val searchTextFieldModule = module {
    single<SearchApiService> {
        SearchApiServiceImpl(httpClient = get())
    }

    single<SearchRepository> {
        SearchRepoImpl(searchApiService = get())
    }

    factory<SearchUseCase> {
        SearchUseCase(repository = get())
    }
}