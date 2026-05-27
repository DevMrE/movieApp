package com.kmp.movieapp.genre.di

import com.kmp.movieapp.genre.data.repository.GenreRepositoryImpl
import com.kmp.movieapp.genre.data.service.GenreService
import com.kmp.movieapp.genre.data.service.GenreServiceImpl
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import org.koin.dsl.module

val genreModule = module {

    single<GenreService> {
        GenreServiceImpl(get())
    }

    single<GenreRepository> {
        GenreRepositoryImpl(get())
    }
}