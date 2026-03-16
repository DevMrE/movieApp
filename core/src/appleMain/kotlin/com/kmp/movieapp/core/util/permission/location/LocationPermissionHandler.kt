package com.kmp.movieapp.core.util.permission.location

import com.kmp.movieapp.core.util.permission.PermissionState
import com.kmp.movieapp.core.util.permission.util.PermissionHandler
import com.kmp.movieapp.core.util.permission.util.suspendPermission
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusRestricted

internal class LocationPermissionHandler : PermissionHandler {

    override suspend fun check() = when (CLLocationManager.authorizationStatus()) {
        kCLAuthorizationStatusAuthorizedAlways,
        kCLAuthorizationStatusAuthorizedWhenInUse -> PermissionState.GRANTED

        kCLAuthorizationStatusDenied,
        kCLAuthorizationStatusRestricted -> PermissionState.PERMANENTLY_DENIED

        else -> PermissionState.NOT_DETERMINED
    }

    override suspend fun request() = suspendPermission<PermissionState> { callback ->
        val manager = CLLocationManager()
        manager.delegate = LocationDelegate { status ->
            callback(
                when (status) {
                    kCLAuthorizationStatusAuthorizedAlways,
                    kCLAuthorizationStatusAuthorizedWhenInUse -> PermissionState.GRANTED

                    kCLAuthorizationStatusDenied,
                    kCLAuthorizationStatusRestricted -> PermissionState.PERMANENTLY_DENIED

                    else -> PermissionState.DENIED
                }
            )
        }
        manager.requestWhenInUseAuthorization()
    }
}
