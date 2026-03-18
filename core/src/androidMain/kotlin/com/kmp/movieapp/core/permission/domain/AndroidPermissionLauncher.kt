package com.kmp.movieapp.core.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Registers and launches Android runtime permission requests.
 *
 * This launcher is bound to a concrete [ComponentActivity] instance and must be
 * registered from the Activity, typically in `onCreate`, before the Activity
 * reaches the STARTED state.
 *
 * Public behavior:
 * - GRANTED: the permission is available.
 * - RETRYABLE_DENIED: the user denied once, but Android may still show the
 *   permission dialog again on a future request.
 * - FINAL_DENIED: the system is no longer expected to show the permission
 *   dialog in a meaningful way and the app should route the user to settings.
 */
class AndroidPermissionLauncher(
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
        mutableMapOf<String, (AndroidPermissionRequestResult) -> Unit>()

    /**
     * Tracks whether a permission request was already launched at least once.
     *
     * On Android, `shouldShowRequestPermissionRationale()` returns false both
     * before the first request and after a final denial, so this state is needed
     * to distinguish those cases.
     */
    private val requestedPermissions = mutableSetOf<String>()

    private var isRegistered = false

    /**
     * Registers all permission launchers.
     *
     * This function is idempotent and safe to call multiple times.
     */
    fun register() {
        if (isRegistered) return

        cameraLauncher =
            activity.registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { result ->
                handleSinglePermissionResult(
                    key = CAMERA_KEY,
                    permission = Manifest.permission.CAMERA,
                    result = result
                )
            }

        locationLauncher =
            activity.registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { result ->
                handleLocationPermissionResult(result)
            }

        microphoneLauncher =
            activity.registerForActivityResult(
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

    /**
     * Requests camera permission.
     */
    fun requestCamera(): Flow<AndroidPermissionRequestResult> =
        request(
            key = CAMERA_KEY,
            launcher = cameraLauncher,
            permissions = arrayOf(
                Manifest.permission.CAMERA
            )
        )

    /**
     * Requests location permission.
     *
     * Fine and coarse location are requested together. The request is considered
     * granted when at least one of them is granted.
     */
    fun requestLocation(): Flow<AndroidPermissionRequestResult> =
        request(
            key = LOCATION_KEY,
            launcher = locationLauncher,
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    /**
     * Requests microphone permission.
     */
    fun requestMicrophone(): Flow<AndroidPermissionRequestResult> =
        request(
            key = MICROPHONE_KEY,
            launcher = microphoneLauncher,
            permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO
            )
        )

    /**
     * Launches a one-shot Android permission request.
     *
     * If at least one required permission is already granted, the flow emits
     * [AndroidPermissionRequestResult.GRANTED] immediately and does not show
     * a system dialog.
     */
    private fun request(
        key: String,
        launcher: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ): Flow<AndroidPermissionRequestResult> = callbackFlow {

        val alreadyGranted = permissions.any { permission ->
            ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (alreadyGranted) {
            trySend(AndroidPermissionRequestResult.GRANTED)
            close()
            return@callbackFlow
        }

        callbacks[key] = { result ->
            trySend(result)
            close()
        }

        permissions.forEach(requestedPermissions::add)
        launcher.launch(permissions)

        awaitClose {
            callbacks.remove(key)
        }
    }

    /**
     * Maps a single-permission result such as camera or microphone.
     */
    private fun handleSinglePermissionResult(
        key: String,
        permission: String,
        result: Map<String, Boolean>
    ) {
        val granted = result[permission] == true

        val mappedResult =
            when {
                granted -> AndroidPermissionRequestResult.GRANTED
                shouldRouteToSettings(permission) -> AndroidPermissionRequestResult.FINAL_DENIED
                else -> AndroidPermissionRequestResult.RETRYABLE_DENIED
            }

        callbacks[key]?.invoke(mappedResult)
    }

    /**
     * Maps the combined location permission result.
     */
    private fun handleLocationPermissionResult(
        result: Map<String, Boolean>
    ) {
        val fineGranted = result[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = result[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        val mappedResult =
            when {
                fineGranted || coarseGranted -> AndroidPermissionRequestResult.GRANTED

                shouldRouteToSettings(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        shouldRouteToSettings(Manifest.permission.ACCESS_COARSE_LOCATION) ->
                    AndroidPermissionRequestResult.FINAL_DENIED

                else -> AndroidPermissionRequestResult.RETRYABLE_DENIED
            }

        callbacks[LOCATION_KEY]?.invoke(mappedResult)
    }

    /**
     * Returns true when Android is no longer expected to show a meaningful
     * permission dialog for the given permission.
     */
    private fun shouldRouteToSettings(permission: String): Boolean {
        val wasRequestedBefore = requestedPermissions.contains(permission)
        val shouldShowRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

        return wasRequestedBefore && !shouldShowRationale
    }
}

/**
 * Internal Android-only permission request state.
 *
 * This type is intentionally kept out of the public app-layer API.
 */
enum class AndroidPermissionRequestResult {
    GRANTED,
    RETRYABLE_DENIED,
    FINAL_DENIED
}