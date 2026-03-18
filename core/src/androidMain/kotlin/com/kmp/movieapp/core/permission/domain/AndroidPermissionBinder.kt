package com.kmp.movieapp.core.permission.domain

import androidx.activity.ComponentActivity
import com.kmp.movieapp.core.permission.AndroidPermissionLauncher
import com.kmp.movieapp.core.util.ActivityProvider

/**
 * Binds all Android permission-related infrastructure to the current Activity.
 *
 * This class centralizes lifecycle-bound registration logic so the Activity
 * only needs a single entry point.
 */
class AndroidPermissionBinder(
    private val permissionsController: PermissionsController,
    private val androidGalleryProvider: AndroidGalleryProvider
) {

    /**
     * Binds the current [activity] to all Android permission components.
     *
     * This function must be called from the hosting Activity during `onCreate`.
     */
    fun bind(activity: ComponentActivity) {
        ActivityProvider.activity = activity

        val androidPermissionLauncher = AndroidPermissionLauncher(activity)
        androidPermissionLauncher.register()

        androidGalleryProvider.register(activity)

        (permissionsController as? AndroidPermissionsController)
            ?.bindLauncher(androidPermissionLauncher)
    }

    /**
     * Clears the current Activity reference when the bound Activity is destroyed.
     */
    fun unbind(activity: ComponentActivity) {
        if (ActivityProvider.activity === activity) {
            ActivityProvider.activity = null
        }
    }
}