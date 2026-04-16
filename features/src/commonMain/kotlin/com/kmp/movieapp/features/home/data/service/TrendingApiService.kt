package com.kmp.movieapp.features.home.data.service

interface TrendingApiService {

    suspend fun fetchAll()

    suspend fun fetchMovies()

    suspend fun fetchSeries()
}