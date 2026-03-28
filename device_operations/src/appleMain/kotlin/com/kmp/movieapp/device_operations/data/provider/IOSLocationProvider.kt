package com.kmp.movieapp.device_operations.data.provider

import com.kmp.movieapp.device_operations.data.util.createCallbackFlow
import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Location
import com.kmp.movieapp.device_operations.domain.provider.LocationProvider
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.Flow
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.Foundation.NSError
import platform.darwin.NSObject

/**
 * iOS implementation of [LocationProvider].
 *
 * Expects location permission to be granted before use.
 */
@OptIn(ExperimentalForeignApi::class)
internal class IOSLocationProvider : LocationProvider {

    /**
     * Resolves the current location once.
     */
    override fun getCurrentLocation(): Flow<Location> {
        val locationManager = CLLocationManager()
        var callback: ((Location) -> Unit)? = null

        val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
            override fun locationManager(
                manager: CLLocationManager,
                didUpdateLocations: List<*>
            ) {
                val location = didUpdateLocations.lastOrNull() as? CLLocation ?: return

                callback?.invoke(
                    location.coordinate.useContents {
                        Location(
                            latitude = latitude,
                            longitude = longitude
                        )
                    }
                )
            }

            override fun locationManager(
                manager: CLLocationManager,
                didFailWithError: NSError
            ) {
                // Intentionally ignored.
                // The flow remains silent if no location could be resolved.
            }
        }

        locationManager.delegate = delegate

        return createOneShotFlow(
            block = { send ->
                callback = send
                locationManager.requestLocation()
            },
            onClose = {
                callback = null
                locationManager.delegate = null
            }
        )
    }

    /**
     * Emits continuous location updates until the flow is cancelled.
     */
    override fun getLocationUpdates(): Flow<Location> {
        val locationManager = CLLocationManager()
        var callback: ((Location) -> Unit)? = null

        val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
            override fun locationManager(
                manager: CLLocationManager,
                didUpdateLocations: List<*>
            ) {
                val location = didUpdateLocations.lastOrNull() as? CLLocation ?: return

                callback?.invoke(
                    location.coordinate.useContents {
                        Location(
                            latitude = latitude,
                            longitude = longitude
                        )
                    }
                )
            }

            override fun locationManager(
                manager: CLLocationManager,
                didFailWithError: NSError
            ) {
                // Intentionally ignored.
                // The stream stays active unless explicitly cancelled.
            }
        }

        locationManager.delegate = delegate

        return createCallbackFlow(
            block = { send, close ->
                callback = send
                locationManager.startUpdatingLocation()
            },
            onClose = {
                locationManager.stopUpdatingLocation()
                callback = null
                locationManager.delegate = null
            }
        )
    }
}