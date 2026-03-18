package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.Foundation.NSError
import platform.darwin.NSObject

/**
 * Resolves the current iOS location using [CLLocationManager].
 *
 * This provider handles both authorization and location callbacks through
 * a dedicated delegate implementation.
 */
@OptIn(ExperimentalForeignApi::class)
class IOSLocationProvider {

    private val manager = CLLocationManager()
    private var callback: ((PermissionResult<Location>) -> Unit)? = null

    private val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
        override fun locationManager(
            manager: CLLocationManager,
            didUpdateLocations: List<*>
        ) {
            val location = didUpdateLocations.lastOrNull() as? CLLocation

            if (location == null) {
                callback?.invoke(PermissionResult(PermissionStatus.DENIED))
                return
            }

            val mappedLocation = location.coordinate.useContents {
                Location(
                    latitude = latitude,
                    longitude = longitude
                )
            }

            callback?.invoke(
                PermissionResult(
                    status = PermissionStatus.GRANTED,
                    data = mappedLocation
                )
            )
        }

        override fun locationManager(
            manager: CLLocationManager,
            didFailWithError: NSError
        ) {
            callback?.invoke(PermissionResult(PermissionStatus.DENIED))
        }
    }

    init {
        manager.delegate = delegate
    }

    fun getLocation(): Flow<PermissionResult<Location>> = callbackFlow {
        callback = { result ->
            trySend(result)
            close()
        }

        manager.requestWhenInUseAuthorization()
        manager.requestLocation()

        awaitClose {
            callback = null
        }
    }
}