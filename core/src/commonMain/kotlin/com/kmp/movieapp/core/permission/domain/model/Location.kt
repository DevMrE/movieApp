package com.kmp.movieapp.core.permission.domain.model

/**
 * Represents a single location fix from the device.
 *
 * @property latitude Latitude in degrees.
 * @property longitude Longitude in degrees.
 */
data class Location(
    val latitude: Double,
    val longitude: Double
)
