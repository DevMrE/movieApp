package com.kmp.movieapp.device_operations.data.provider

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Location
import com.kmp.movieapp.device_operations.domain.provider.LocationProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Android implementation of [LocationProvider].
 *
 * Expects location permission to be granted before usage.
 */
internal class AndroidLocationProvider(
    context: Context
) : LocationProvider {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    /**
     * Returns the current location once.
     */
    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<Location> = createOneShotFlow(
        block = { send ->
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                CurrentLocationRequest.Builder()
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .build(),
                cancellationTokenSource.token
            ).addOnSuccessListener { androidLocation ->
                androidLocation?.let {
                    send(
                        Location(
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    )
                }
            }.addOnFailureListener {
                cancellationTokenSource.cancel()
            }
        }
    )

    /**
     * Emits continuous location updates until the flow is cancelled.
     */
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(): Flow<Location> = callbackFlow {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            LOCATION_UPDATE_INTERVAL
        ).setMinUpdateIntervalMillis(
            LOCATION_UPDATE_MIN_INTERVAL
        ).build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation ?: return

                trySend(
                    Location(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                ).isSuccess
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            callback,
            Looper.getMainLooper()
        ).addOnFailureListener {
            close()
        }

        awaitClose {
            fusedLocationClient.removeLocationUpdates(callback)
        }
    }

    private companion object {
        const val LOCATION_UPDATE_INTERVAL = 5_000L
        const val LOCATION_UPDATE_MIN_INTERVAL = 2_000L
    }
}