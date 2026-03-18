package com.kmp.movieapp.core.permission.domain

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import co.touchlab.kermit.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Registers and launches Android runtime permission requests.
 *
 * All permission requests are internally handled via
 * [ActivityResultContracts.RequestMultiplePermissions], even if only one
 * permission is needed. This keeps the implementation consistent and avoids
 * having multiple callback styles.
 *
 * Important:
 * - [register] must be called once from the hosting [ComponentActivity],
 *   typically in `onCreate`, before the Activity reaches the STARTED state.
 * - This class only handles permission requests.
 * - Feature execution such as location retrieval or opening the gallery should
 *   be delegated to dedicated provider classes.
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

    private val callbacks = mutableMapOf<String, (Boolean) -> Unit>()

    /**
     * Registers all required activity result launchers.
     *
     * This function is idempotent. Calling it more than once is safe, but only
     * the first call performs the actual registration.
     */
    fun register() = try {
        cameraLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            val granted = result[Manifest.permission.CAMERA] == true
            callbacks[CAMERA_KEY]?.invoke(granted)
        }

        locationLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            val fineGranted = result[Manifest.permission.ACCESS_FINE_LOCATION] == true
            val coarseGranted = result[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            callbacks[LOCATION_KEY]?.invoke(fineGranted || coarseGranted)
        }

        microphoneLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            val granted = result[Manifest.permission.RECORD_AUDIO] == true
            callbacks[MICROPHONE_KEY]?.invoke(granted)
        }
    } catch (e: Exception) {
        Logger.e(
            tag = "Permission",
            messageString = "Error during permission registration",
            throwable = e
        )
    }

    /**
     * Requests camera permission.
     *
     * Emits `true` when the permission is available, otherwise `false`.
     */
    fun requestCamera(): Flow<Boolean> =
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
     * This request asks for both fine and coarse location access. The emitted
     * value is `true` when at least one of them is granted.
     */
    fun requestLocation(): Flow<Boolean> =
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
     *
     * Emits `true` when the permission is available, otherwise `false`.
     */
    fun requestMicrophone(): Flow<Boolean> =
        request(
            key = MICROPHONE_KEY,
            launcher = microphoneLauncher,
            permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO
            )
        )

    /**
     * Launches a one-shot runtime permission request and emits the final
     * availability state.
     *
     * If all required permissions are already granted, the flow returns `true`
     * immediately without opening any system dialog.
     */
    private fun request(
        key: String,
        launcher: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ): Flow<Boolean> = callbackFlow {
        val allGranted = permissions.all { permission ->
            val granted = ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED

            val selfPermission = ContextCompat.checkSelfPermission(
                activity,
                permission
            )

            Logger.i(
                tag = "Permission",
                messageString = "selfPermission: $selfPermission"
            )

            Logger.i(
                tag = "Permission",
                messageString = "permission: $permission granted?: $granted"
            )

            granted
        }

        if (allGranted) {
            trySend(true)
            close()
            return@callbackFlow
        }

        callbacks[key] = { granted ->
            trySend(granted)
            close()
        }

        launcher.launch(permissions)

        awaitClose {
            callbacks.remove(key)
        }
    }
}