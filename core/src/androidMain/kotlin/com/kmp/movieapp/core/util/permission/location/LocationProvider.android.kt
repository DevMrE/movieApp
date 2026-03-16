package com.kmp.movieapp.core.util.permission.location

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


actual class LocationProvider(
    private val context: Context
) {
    actual suspend fun getCurrentLocation(): Result<Location> {

        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            return Result.failure(Error("Location Permission not granted"))
        }

        val client = LocationServices.getFusedLocationProviderClient(context)
        val token = CancellationTokenSource()

        return suspendCancellableCoroutine { continuation ->
            client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token.token)
                .addOnSuccessListener { androidLocation ->
                    if (androidLocation != null) {
                        continuation.resume(
                            value = Result.success(
                                Location(
                                    lat = androidLocation.latitude,
                                    lng = androidLocation.longitude
                                )
                            )
                        )
                    } else {
                        continuation.resume(
                            Result.failure(Error("Location not available"))
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(Error("Location Error")))
                }

            continuation.invokeOnCancellation { token.cancel() }
        }
    }
}