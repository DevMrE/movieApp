package com.kmp.movieapp.movie.data.service

import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.data.model.ApiResponseDto
import com.kmp.movieapp.movie.data.model.exception.MovieNotFoundException
import com.kmp.movieapp.movie.data.model.request.MovieGenreRequestDto
import com.kmp.movieapp.movie.data.model.request.MovieListCategory
import com.kmp.movieapp.movie.data.model.request.MovieListRequestDto
import com.kmp.movieapp.movie.data.model.response.MovieDto
import com.kmp.movieapp.movie.data.model.response.MovieGenreResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {

    override suspend fun fetchMoviesForCategory(
        language: String,
        page: Int,
        movieListCategory: MovieListCategory
    ): Result<ApiResponseDto<MovieDto>> =
        withContext(Dispatchers.IO) {
            try {
                val response = httpClient.get(
                    resource = MovieListRequestDto(
                        page = page,
                        language = "de",
                        movieListCategory = movieListCategory.category
                    )
                )

                if (response.status == HttpStatusCode.OK) {
                    Result.success(response.body())
                } else Result.failure(MovieNotFoundException())

            } catch (e: Exception) {
                Logger.e(messageString = e.message.toString())

                Result.failure(e)
            }
        }

    override suspend fun fetchMovieGenres(language: String): Result<MovieGenreResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.get(
                    resource = MovieGenreRequestDto(language = language)
                )

                if (response.status == HttpStatusCode.OK) {
                    Result.success(response.body())
                } else Result.failure(MovieNotFoundException())

            } catch (e: Exception) {
                Logger.e(messageString = e.message.toString())
                Result.failure(e)
            }
        }
    }
}