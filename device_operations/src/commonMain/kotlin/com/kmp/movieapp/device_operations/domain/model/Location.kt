package com.kmp.movieapp.device_operations.domain.model

/**
 * Represents a geographic location.
 *
 * @param latitude Latitude coordinate.
 * @param longitude Longitude coordinate.
 */
data class Location(
    val latitude: Double,
    val longitude: Double
)