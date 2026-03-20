package com.kmp.movieapp.device_operations.data.permission

import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import kotlinx.coroutines.flow.Flow
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVAuthorizationStatusRestricted
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.darwin.NSObject

/**
 * iOS implementation of [IOSPermissionGateway].
 *
 * Uses modern iOS permission APIs and maps the result directly
 * to the shared [PermissionState].
 */
internal class IOSPermissionGatewayImpl : IOSPermissionGateway {

    /**
     * Requests camera permission.
     */
    override fun requestCameraPermission(): Flow<PermissionState> = createOneShotFlow(
        block = { send ->
            when (AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)) {
                AVAuthorizationStatusAuthorized -> {
                    send(PermissionState.GRANTED)
                }

                AVAuthorizationStatusDenied,
                AVAuthorizationStatusRestricted -> {
                    send(PermissionState.FINAL_DENIED)
                }

                AVAuthorizationStatusNotDetermined -> {
                    AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) { granted ->
                        if (granted) {
                            send(PermissionState.GRANTED)
                        } else {
                            send(PermissionState.FINAL_DENIED)
                        }
                    }
                }

                else -> {
                    send(PermissionState.FINAL_DENIED)
                }
            }
        }
    )

    /**
     * Requests location permission.
     */
    override fun requestLocationPermission(): Flow<PermissionState> {
        val locationManager = CLLocationManager()
        var callback: ((PermissionState) -> Unit)? = null

        val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
            override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
                when (manager.authorizationStatus) {
                    kCLAuthorizationStatusAuthorizedAlways,
                    kCLAuthorizationStatusAuthorizedWhenInUse -> {
                        callback?.invoke(PermissionState.GRANTED)
                    }

                    kCLAuthorizationStatusDenied,
                    kCLAuthorizationStatusRestricted -> {
                        callback?.invoke(PermissionState.FINAL_DENIED)
                    }

                    kCLAuthorizationStatusNotDetermined -> Unit

                    else -> {
                        callback?.invoke(PermissionState.FINAL_DENIED)
                    }
                }
            }
        }

        locationManager.delegate = delegate

        return createOneShotFlow(
            block = { send ->
                callback = send

                when (locationManager.authorizationStatus) {
                    kCLAuthorizationStatusAuthorizedAlways,
                    kCLAuthorizationStatusAuthorizedWhenInUse -> {
                        send(PermissionState.GRANTED)
                    }

                    kCLAuthorizationStatusDenied,
                    kCLAuthorizationStatusRestricted -> {
                        send(PermissionState.FINAL_DENIED)
                    }

                    kCLAuthorizationStatusNotDetermined -> {
                        locationManager.requestWhenInUseAuthorization()
                    }

                    else -> {
                        send(PermissionState.FINAL_DENIED)
                    }
                }
            },
            onClose = {
                callback = null
                locationManager.delegate = null
            }
        )
    }
}