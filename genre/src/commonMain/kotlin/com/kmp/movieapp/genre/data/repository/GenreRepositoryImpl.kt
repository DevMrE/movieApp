package com.kmp.movieapp.genre.data.repository

import com.kmp.movieapp.core.network.util.onSuccess
import com.kmp.movieapp.genre.data.model.mapper.toGenreList
import com.kmp.movieapp.genre.data.service.GenreService
import com.kmp.movieapp.genre.domain.model.Genre
import com.kmp.movieapp.genre.domain.model.GenreContentType
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class GenreRepositoryImpl(
    private val genreService: GenreService
) : GenreRepository {

    private val _movieGenres = MutableStateFlow<List<Genre>>(emptyList())
    private val _seriesGenres = MutableStateFlow<List<Genre>>(emptyList())

    override val movieGenres: StateFlow<List<Genre>> = _movieGenres
        .onStart {
            genreService.fetchGenres(GenreContentType.MOVIE)
                .onSuccess { dtoList ->
                    _movieGenres.update { dtoList.toGenreList() }
                }
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.Eagerly,
            initialValue = _movieGenres.value
        )

    override val seriesGenres: StateFlow<List<Genre>> = _seriesGenres
        .onStart {
            genreService.fetchGenres(GenreContentType.SERIES)
                .onSuccess { dtoList ->
                    _seriesGenres.update { dtoList.toGenreList() }
                }
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.Eagerly,
            initialValue = _seriesGenres.value
        )
}