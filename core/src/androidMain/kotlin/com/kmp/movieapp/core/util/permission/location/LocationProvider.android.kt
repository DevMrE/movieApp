package com.kmp.movieapp.core.util.permission.location

import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive


actual class LocationProvider(
    private val context: Context
) {
    actual suspend fun getCurrentLocation(): Result<Location> {
        return try {
            val location = locationFlow().first()
            Result.success(location)
        } catch (e: NoSuchElementException) {
            Result.failure(Error("Location not available"))
        } catch (e: SecurityException) {
            Result.failure(Error("Location Permission not granted"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun locationFlow(): Flow<Location> = callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(context)

        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000L)
            .setMaxUpdates(1)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { loc ->
                    val sendResult = trySend(Location(lat = loc.latitude, lng = loc.longitude))
                    if (sendResult.isFailure) close(Exception("Failed to send location"))
                } ?: close(Error("Location not available"))
            }

            override fun onLocationAvailability(availability: LocationAvailability) {
                if (!availability.isLocationAvailable && isActive) {
                    close(Error("Location not available"))
                }
            }
        }

        // Lint kann den Check jetzt direkt sehen
        val hasPermission = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).any { permission ->
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (!hasPermission) {
            close(SecurityException("Location permission not granted"))
            return@callbackFlow
        }

        try {
            client.requestLocationUpdates(request, callback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            close(e)
            return@callbackFlow
        }

        awaitClose { client.removeLocationUpdates(callback) }
    }
}