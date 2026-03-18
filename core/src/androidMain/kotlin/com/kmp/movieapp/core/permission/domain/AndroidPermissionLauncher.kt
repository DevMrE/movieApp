package com.kmp.movieapp.core.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.permission.domain.AndroidPermissionState
import com.kmp.movieapp.core.permission.util.createPermissionFlow
import kotlinx.coroutines.flow.Flow

/**
 * Registers and launches Android runtime permission requests.
 *
 * This launcher is bound to a concrete [ComponentActivity] instance.
 */
internal class AndroidPermissionLauncher(
    private val activity: ComponentActivity
) {

    private companion object {
        private const val CAMERA_KEY = "camera"
        private const val LOCATION_KEY = "location"
        private const val MICROPHONE_KEY = "microphone"
    }

    private lateinit var cameraLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var locationLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var microphoneLauncher: ActivityResultLauncher<Array<String>>

    private val callbacks =
        mutableMapOf<String, (AndroidPermissionState) -> Unit>()

    private val requestedPermissions = mutableSetOf<String>()

    private var isRegistered = false

    fun register() {
        if (isRegistered) return

        cameraLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            handleSinglePermissionResult(
                key = CAMERA_KEY,
                permission = Manifest.permission.CAMERA,
                result = result
            )
        }

        locationLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            handleLocationPermissionResult(result)
        }

        microphoneLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            handleSinglePermissionResult(
                key = MICROPHONE_KEY,
                permission = Manifest.permission.RECORD_AUDIO,
                result = result
            )
        }

        isRegistered = true
    }

    fun requestCamera(): Flow<AndroidPermissionState> =
        request(
            key = CAMERA_KEY,
            launcher = cameraLauncher,
            permissions = arrayOf(Manifest.permission.CAMERA)
        )

    fun requestLocation(): Flow<AndroidPermissionState> =
        request(
            key = LOCATION_KEY,
            launcher = locationLauncher,
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    fun requestMicrophone(): Flow<AndroidPermissionState> =
        request(
            key = MICROPHONE_KEY,
            launcher = microphoneLauncher,
            permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
        )

    private fun request(
        key: String,
        launcher: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ): Flow<AndroidPermissionState> = createPermissionFlow(
        awaitClose = { callbacks.remove(key) }
    ) { send ->

        val alreadyGranted = permissions.any { permission ->
            val granted = ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED

            Logger.i(
                tag = "Permission",
                messageString = "Requested permission: $permission, granted?: $granted"
            )
            granted
        }

        if (alreadyGranted) {
            send(AndroidPermissionState.GRANTED)
            return@createPermissionFlow
        }

        callbacks[key] = { result ->
            send(result)
        }

        permissions.forEach(requestedPermissions::add)
        launcher.launch(permissions)
    }

    private fun handleSinglePermissionResult(
        key: String,
        permission: String,
        result: Map<String, Boolean>
    ) {
        val granted = result[permission] == true

        val mappedResult =
            when {
                granted -> AndroidPermissionState.GRANTED
                shouldRouteToSettings(permission) -> AndroidPermissionState.FINAL_DENIED
                else -> AndroidPermissionState.RETRYABLE_DENIED
            }

        callbacks[key]?.invoke(mappedResult)
    }

    private fun handleLocationPermissionResult(
        result: Map<String, Boolean>
    ) {
        val fineGranted = result[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = result[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        val mappedResult =
            when {
                fineGranted || coarseGranted -> AndroidPermissionState.GRANTED
                shouldRouteToSettings(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        shouldRouteToSettings(Manifest.permission.ACCESS_COARSE_LOCATION) ->
                    AndroidPermissionState.FINAL_DENIED

                else -> AndroidPermissionState.RETRYABLE_DENIED
            }

        callbacks[LOCATION_KEY]?.invoke(mappedResult)
    }

    private fun shouldRouteToSettings(permission: String): Boolean {
        val wasRequestedBefore = requestedPermissions.contains(permission)
        val shouldShowRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

        return wasRequestedBefore && !shouldShowRationale
    }
}
