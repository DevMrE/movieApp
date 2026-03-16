package com.kmp.movieapp.core.util.permission

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.darwin.NSObject
import kotlin.coroutines.resume

class LocationPermissionRequester : CLLocationManagerDelegateProtocol, NSObject() {

    private val locationManager = CLLocationManager()
    private var continuation: CancellableContinuation<PermissionState>? = null

    suspend fun request(): PermissionState {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            locationManager.delegate = this
            locationManager.requestWhenInUseAuthorization()
        }
    }

    override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
        val state = when (manager.authorizationStatus) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse -> PermissionState.GRANTED
            kCLAuthorizationStatusDenied -> PermissionState.PERMANENTLY_DENIED
            else -> PermissionState.NOT_DETERMINED
        }
        continuation?.resume(state)
        continuation = null
    }
}