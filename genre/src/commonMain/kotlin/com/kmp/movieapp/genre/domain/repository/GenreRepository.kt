package com.kmp.movieapp.genre.domain.repository

import com.kmp.movieapp.genre.domain.model.Genre
import kotlinx.coroutines.flow.StateFlow

interface GenreRepository {

    val movieGenres: StateFlow<List<Genre>>

    val seriesGenres: StateFlow<List<Genre>>

}