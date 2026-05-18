package com.kmp.movieapp.content_detail.di

import com.kmp.movieapp.content_detail.data.repository.api.ContentDetailRepImpl
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetailUseCase
import com.kmp.movieapp.content_detail.presentation.ContentDetailViewModel
import com.kmp.movieapp.core.ui.content.model.MediaCategory
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val contentDetailModule = module {

    single<ContentDetailRepository> {
        ContentDetailRepImpl(get(), get())
    }

    factory<GetContentDetailUseCase> {
        GetContentDetailUseCase(get())
    }

    viewModel<ContentDetailViewModel> { (id: String, mediaCategory: MediaCategory) ->
        ContentDetailViewModel(
            id = id,
            mediaCategory = mediaCategory,
            getContentDetailUseCase = get()
        )
    }
}