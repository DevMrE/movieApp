package com.kmp.movieapp.core.util.permission.location

data class Location(val lat: Double, val lng: Double)

expect class LocationProvider {

    suspend fun getCurrentLocation(): Result<Location>
}