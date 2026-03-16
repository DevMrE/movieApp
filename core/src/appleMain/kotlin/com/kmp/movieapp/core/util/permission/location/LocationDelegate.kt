package com.kmp.movieapp.core.util.permission.location

import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.darwin.NSObject

// MARK: - CLLocationManager Delegate
internal class LocationDelegate(
    private val onStatusChanged: (CLAuthorizationStatus) -> Unit
) : NSObject(), CLLocationManagerDelegateProtocol {

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        if (didChangeAuthorizationStatus != kCLAuthorizationStatusNotDetermined) {
            onStatusChanged(didChangeAuthorizationStatus)
        }
    }
}