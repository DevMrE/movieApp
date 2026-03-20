package com.kmp.movieapp.device_operations.platform

import android.app.Activity
import androidx.activity.ComponentActivity
import com.kmp.movieapp.device_operations.data.provider.AndroidCameraProvider
import com.kmp.movieapp.device_operations.data.provider.AndroidGalleryProvider
import com.kmp.movieapp.device_operations.platform.permission.AndroidPermissionLauncher

/**
 * Central binder for all Activity-dependent Android device operation components.
 *
 * This class registers the permission launcher and all providers that need access
 * to the activity:
 * - the permission launcher
 * - the camera provider
 * - the gallery provider
 *
 * It must be called from Activity.onCreate().
 */
class AndroidDeviceOperationsBinder internal constructor(
    private val androidCameraProvider: AndroidCameraProvider,
    private val androidGalleryProvider: AndroidGalleryProvider
) {

    private var activity: ComponentActivity? = null
    private var androidPermissionLauncher: AndroidPermissionLauncher? = null

    /**
     * Binds all required Android components to the given [activity].
     */
    fun bind(activity: ComponentActivity) {
        this.activity = activity

        if (androidPermissionLauncher == null) {
            androidPermissionLauncher = AndroidPermissionLauncher(activity).also { launcher ->
                launcher.register()
            }
        }

        androidCameraProvider.register(activity)
        androidGalleryProvider.register(activity)
    }

    /**
     * Returns the currently bound Activity for internal Android components.
     */
    internal fun getActivity(): Activity? = activity

    /**
     * Returns the registered Android permission launcher for internal Android components.
     */
    internal fun getPermissionLauncher(): AndroidPermissionLauncher? = androidPermissionLauncher

    /**
     * Clears the current binding when the given [activity] is destroyed.
     */
    fun unbind(activity: ComponentActivity) {
        if (this.activity === activity) {
            this.activity = null
            this.androidPermissionLauncher = null
        }
    }
}