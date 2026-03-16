package com.kmp.movieapp.core.util.permission.location

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject
import kotlin.coroutines.resume

actual class LocationProvider {

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun getCurrentLocation(): Result<Location> {
        return suspendCancellableCoroutine { continuation ->
            val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(
                    manager: CLLocationManager,
                    didUpdateLocations: List<*>
                ) {
                    val location = didUpdateLocations.lastOrNull() as? CLLocation
                    if (location != null) {
                        continuation.resume(
                            Result.success(
                                Location(
                                    lat = location.coordinate.useContents { latitude },
                                    lng = location.coordinate.useContents { longitude }
                                )
                            )
                        )
                    } else {
                        continuation.resume(Result.failure(Error("Location nicht verfügbar")))
                    }
                    manager.stopUpdatingLocation()
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: platform.Foundation.NSError
                ) {
                    continuation.resume(
                        Result.failure(Error(didFailWithError.localizedDescription))
                    )
                }
            }

            CLLocationManager().apply {
                this.delegate = delegate
                requestWhenInUseAuthorization()
                startUpdatingLocation()
            }
        }
    }
}