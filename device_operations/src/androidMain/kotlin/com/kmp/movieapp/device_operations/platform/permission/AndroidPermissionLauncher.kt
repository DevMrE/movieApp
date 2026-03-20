package com.kmp.movieapp.device_operations.platform.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

/**
 * Wraps Android's Activity Result API for runtime permission requests.
 *
 * This launcher must be created with a concrete [ComponentActivity] and
 * registered from `onCreate` before the Activity reaches the STARTED state.
 */
internal class AndroidPermissionLauncher(
    private val activity: ComponentActivity
) {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private var callback: ((Map<String, Boolean>) -> Unit)? = null

    private var isRegistered: Boolean = false

    /**
     * Registers the internal Android permission launcher.
     *
     * This function is idempotent and safe to call multiple times.
     */
    fun register() {
        if (isRegistered) return

        permissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            callback?.invoke(result)
            callback = null
        }

        isRegistered = true
    }

    /**
     * Requests the given [permissions] and returns the raw Android result map.
     *
     * Only one active request is expected at a time.
     */
    fun requestPermissions(
        permissions: Array<String>,
        callback: (Map<String, Boolean>) -> Unit
    ) {
        this.callback = callback
        permissionLauncher.launch(permissions)
    }
}