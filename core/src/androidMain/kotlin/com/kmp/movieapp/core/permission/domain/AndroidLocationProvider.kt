package com.kmp.movieapp.core.permission.domain

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.domain.provider.LocationProvider
import com.kmp.movieapp.core.permission.util.PermissionResult
import com.kmp.movieapp.core.permission.util.createPermissionFlow
import com.kmp.movieapp.core.util.ActivityProvider
import kotlinx.coroutines.flow.Flow

/**
 * Resolves the device location after permission has already been granted.
 *
 * This provider does not request permissions by itself.
 */
class AndroidLocationProvider: LocationProvider {

    /**
     * Returns the best available one-shot location result.
     *
     * If location cannot be resolved, the provider still returns GRANTED with
     * null data because permission is already available at this point.
     */
    @SuppressLint("MissingPermission")
    override fun getLocation(): Flow<PermissionResult<Location>> = createPermissionFlow { send ->
        val activity = ActivityProvider.activity
        if (activity == null) {
            send(PermissionResult(PermissionStatus.DENIED))
            return@createPermissionFlow
        }

        val client = LocationServices.getFusedLocationProviderClient(activity)
        val cancellationTokenSource = CancellationTokenSource()

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

                client.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                ).addOnSuccessListener { currentLocation ->
                    val result = if (currentLocation != null) {
                        PermissionResult(
                            status = PermissionStatus.GRANTED,
                            data = Location(
                                latitude = currentLocation.latitude,
                                longitude = currentLocation.longitude
                            )
                        )
                    } else PermissionResult(status = PermissionStatus.GRANTED)

                    send(result)
                }.addOnFailureListener {
                    send(
                        PermissionResult(
                            status = PermissionStatus.GRANTED,
                            data = null
                        )
                    )
                }
            }.addOnFailureListener {
                send(
                    PermissionResult(
                        status = PermissionStatus.GRANTED,
                        data = null
                    )
                )
            }

        } catch (_: SecurityException) {
            send(PermissionResult(status = PermissionStatus.DENIED))
        }
    }
}