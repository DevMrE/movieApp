package com.kmp.movieapp.core.permission.domain

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.util.PermissionResult
import com.kmp.movieapp.core.permission.util.createPermissionFlow
import com.kmp.movieapp.core.util.ActivityProvider
import kotlinx.coroutines.flow.Flow

/**
 * Resolves the device location after permission has already been granted.
 *
 * This provider does not request permissions by itself. It is responsible only
 * for retrieving location data from the platform in a safe way.
 */
class AndroidLocationProvider {

    /**
     * Returns a one-shot flow that emits the best available location result.
     *
     * The provider first tries the last known location. If that is not available,
     * it falls back to a current location request.
     *
     * A denied result here means that location data could not be resolved, not
     * that the permission was refused by the user.
     */
    @SuppressLint("MissingPermission")
    fun getLocation(): Flow<PermissionResult<Location>> = createPermissionFlow { send ->
        val activity = ActivityProvider.activity

        if (activity == null) {
            send(PermissionResult(PermissionStatus.DENIED))
            return@createPermissionFlow
        }

        val client = LocationServices.getFusedLocationProviderClient(activity)

        try {
            client.lastLocation.addOnSuccessListener { lastKnownLocation ->
                if (lastKnownLocation != null) {
                    send(
                        PermissionResult(
                            status = PermissionStatus.GRANTED,
                            data = Location(
                                latitude = lastKnownLocation.latitude,
                                longitude = lastKnownLocation.longitude
                            )
                        )
                    )
                    return@addOnSuccessListener
                }

                client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener { currentLocation ->
                        val result = if (currentLocation != null) {
                            PermissionResult(
                                status = PermissionStatus.GRANTED,
                                data = Location(
                                    latitude = currentLocation.latitude,
                                    longitude = currentLocation.longitude
                                )
                            )
                        } else {
                            PermissionResult(
                                status = PermissionStatus.DENIED
                            )
                        }

                        send(result)
                    }.addOnFailureListener {
                        send(PermissionResult(PermissionStatus.DENIED))
                    }
            }.addOnFailureListener {
                send(PermissionResult(PermissionStatus.DENIED))
            }

        } catch (_: SecurityException) {
            send(PermissionResult(PermissionStatus.DENIED))
        }
    }
}