package com.kmp.movieapp.core.util.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class PermissionRequester(
    private val activity: ComponentActivity
) {
    actual suspend fun checkPermission(permission: Permission): PermissionState {
        val manifestPermissions = permission.toManifestPermissions()
            ?: return PermissionState.GRANTED

        val isGranted = manifestPermissions.any { p ->
            ContextCompat.checkSelfPermission(activity, p) == PackageManager.PERMISSION_GRANTED
        }

        if (isGranted) return PermissionState.GRANTED

        val shouldShow = manifestPermissions.any { p ->
            activity.shouldShowRequestPermissionRationale(p)
        }

        return if (shouldShow) PermissionState.DENIED else PermissionState.NOT_DETERMINED
    }

    actual suspend fun requestPermission(permission: Permission): PermissionState {
        val manifestPermissions = permission.toManifestPermissions()
            ?: return PermissionState.GRANTED

        return suspendCancellableCoroutine { continuation ->
            var launcher: ActivityResultLauncher<Array<String>>? = null

            launcher = activity.activityResultRegistry.register(
                "permission_${permission.name}_${System.currentTimeMillis()}",
                ActivityResultContracts.RequestMultiplePermissions() // <-- Multiple!
            ) { results ->
                launcher?.unregister()

                val isGranted = results.values.any { it } // FINE oder COARSE reicht
                if (isGranted) {
                    continuation.resume(PermissionState.GRANTED)
                    return@register
                }

                val shouldShow = manifestPermissions.any { p ->
                    activity.shouldShowRequestPermissionRationale(p)
                }

                val deniedBefore = activity.getPreferences(Context.MODE_PRIVATE)
                    .getBoolean("permission_asked_${permission.name}", false)

                continuation.resume(
                    if (shouldShow || !deniedBefore) PermissionState.DENIED
                    else PermissionState.PERMANENTLY_DENIED
                )

                activity.getPreferences(Context.MODE_PRIVATE).edit {
                    putBoolean("permission_asked_${permission.name}", true)
                }
            }

            continuation.invokeOnCancellation { launcher?.unregister() }
            launcher.launch(manifestPermissions)
        }
    }

    // String? -> Array<String>? umgestellt
    private fun Permission.toManifestPermissions(): Array<String>? = when (this) {
        Permission.CAMERA -> arrayOf(Manifest.permission.CAMERA)
        Permission.LOCATION -> arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION  // beide zusammen!
        )

        Permission.MICROPHONE -> arrayOf(Manifest.permission.RECORD_AUDIO)
        Permission.GALLERY -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        Permission.NOTIFICATION -> arrayOf(Manifest.permission.POST_NOTIFICATIONS)
    }
}