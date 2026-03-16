package com.kmp.movieapp.core.util.permission.location

actual class LocationProvider {

    actual suspend fun getCurrentLocation(): Result<Location> {
        return Result.failure(Error("Desktop has no location data"))
    }
}