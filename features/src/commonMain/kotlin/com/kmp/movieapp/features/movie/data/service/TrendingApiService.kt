package com.kmp.movieapp.features.movie.data.service

interface TrendingApiService {

    suspend fun fetchAll()

    suspend fun fetchMovies()

    suspend fun fetchSeries()
}