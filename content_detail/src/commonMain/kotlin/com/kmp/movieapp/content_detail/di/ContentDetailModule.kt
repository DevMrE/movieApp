package com.kmp.movieapp.content_detail.di

import com.kmp.movieapp.content_detail.data.repository.api.ContentDetailRepImpl
import com.kmp.movieapp.content_detail.data.service.api.MovieService
import com.kmp.movieapp.content_detail.data.service.api.MovieServiceImpl
import com.kmp.movieapp.content_detail.data.service.api.SeriesService
import com.kmp.movieapp.content_detail.data.service.api.SeriesServiceImpl
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetail
import com.kmp.movieapp.content_detail.presentation.ContentDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val contentDetailModule = module {

    single<MovieService> {
        MovieServiceImpl(get())
    }

    single<SeriesService> {
        SeriesServiceImpl(get())
    }

    single<ContentDetailRepository> {
        ContentDetailRepImpl(get(), get())
    }

    factory<GetContentDetail> {
        GetContentDetail(get())
    }

    viewModelOf(::ContentDetailViewModel)
}