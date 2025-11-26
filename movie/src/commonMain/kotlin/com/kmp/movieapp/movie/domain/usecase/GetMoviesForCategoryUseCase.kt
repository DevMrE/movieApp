package com.kmp.movieapp.movie.domain.usecase

import com.kmp.movieapp.movie.domain.model.MovieCategory
import com.kmp.movieapp.movie.domain.repository.MovieRepository

class GetMoviesForCategoryUseCase(
    private val movieRepository: MovieRepository
) {

     suspend operator fun invoke(movieCategory: MovieCategory) =
        movieRepository.getMovies(language = "", movieCategory = movieCategory)

}