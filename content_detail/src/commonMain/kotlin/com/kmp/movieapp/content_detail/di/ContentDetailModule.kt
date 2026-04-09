package com.kmp.movieapp.content_detail.di

import com.kmp.movieapp.content_detail.data.repository.api.ContentDetailRepImpl
import com.kmp.movieapp.content_detail.domain.repository.ContentDetailRepository
import com.kmp.movieapp.content_detail.domain.usecase.GetContentDetail
import com.kmp.movieapp.content_detail.presentation.ContentDetailViewModel
import com.kmp.movieapp.core.content_type.model.ContentDetailType
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val contentDetailModule = module {

    single<ContentDetailRepository> {
        ContentDetailRepImpl(get(), get())
    }

    factory<GetContentDetail> {
        GetContentDetail(get())
    }

    viewModel<ContentDetailViewModel> { (id: String, contentType: ContentDetailType) ->
        ContentDetailViewModel(
            getContentDetail = get(),
            id = id,
            contentType = contentType
        )
    }
}