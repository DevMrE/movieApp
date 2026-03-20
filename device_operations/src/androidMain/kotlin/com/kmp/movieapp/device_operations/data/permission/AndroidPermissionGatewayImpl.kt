package com.kmp.movieapp.device_operations.data.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.platform.AndroidDeviceOperationsBinder
import kotlinx.coroutines.flow.Flow

/**
 * Android implementation of [AndroidPermissionGateway].
 *
 * Uses the bound Activity and registered permission launcher from
 * [AndroidDeviceOperationsBinder] to request runtime permissions safely.
 */
internal class AndroidPermissionGatewayImpl(
    private val androidDeviceOperationsBinder: AndroidDeviceOperationsBinder
) : AndroidPermissionGateway {

    /**
     * Requests camera permission.
     */
    override fun requestCameraPermission(): Flow<AndroidPermissionState> {
        return request(
            permissions = arrayOf(
                Manifest.permission.CAMERA
            )
        )
    }

    /**
     * Requests location permission.
     */
    override fun requestLocationPermission(): Flow<AndroidPermissionState> {
        return request(
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    /**
     * Executes the shared Android permission request flow.
     */
    private fun request(
        permissions: Array<String>
    ): Flow<AndroidPermissionState> = createOneShotFlow(
        block = { send ->
            val activity = androidDeviceOperationsBinder.getActivity()
            val launcher = androidDeviceOperationsBinder.getPermissionLauncher()

            if (activity == null || launcher == null) {
                send(AndroidPermissionState.FINAL_DENIED)
                return@createOneShotFlow
            }

            val alreadyGranted = permissions.all { permission ->
                ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }

            if (alreadyGranted) {
                send(AndroidPermissionState.GRANTED)
                return@createOneShotFlow
            }

            launcher.requestPermissions(
                permissions = permissions
            ) { resultMap ->
                val granted = resultMap.all { it.value }

                if (granted) {
                    send(AndroidPermissionState.GRANTED)
                    return@requestPermissions
                }

                val shouldShowRationale = permissions.any { permission ->
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permission
                    )
                }

                val state = if (shouldShowRationale) {
                    AndroidPermissionState.RETRYABLE_DENIED
                } else {
                    AndroidPermissionState.FINAL_DENIED
                }

                send(state)
            }
        }
    )
}