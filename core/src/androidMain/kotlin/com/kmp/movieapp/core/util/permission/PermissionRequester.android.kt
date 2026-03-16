package com.kmp.movieapp.core.util.permission

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class PermissionRequester(
    private val activity: ComponentActivity
) {
    actual suspend fun checkPermission(permission: Permission): PermissionState {
        val manifestPermission = permission.toManifestPermission()
            ?: return PermissionState.GRANTED

        return when {
            ContextCompat.checkSelfPermission(
                activity,
                manifestPermission
            ) == PackageManager.PERMISSION_GRANTED
                -> PermissionState.GRANTED

            activity.shouldShowRequestPermissionRationale(manifestPermission)
                -> PermissionState.DENIED

            else -> PermissionState.NOT_DETERMINED
        }
    }

    actual suspend fun requestPermission(permission: Permission): PermissionState {
        val manifestPermission = permission.toManifestPermission()
            ?: return PermissionState.GRANTED

        return suspendCancellableCoroutine { continuation ->
            val launcher = activity.activityResultRegistry.register(
                "permission_${permission.name}",
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                continuation.resume(
                    if (isGranted) {
                        PermissionState.GRANTED
                    } else if (activity.shouldShowRequestPermissionRationale(manifestPermission)) {
                        PermissionState.DENIED
                    } else {
                        PermissionState.PERMANENTLY_DENIED
                    }
                )
            }
            launcher.launch(manifestPermission)
        }
    }

    private fun Permission.toManifestPermission(): String? = when (this) {
        Permission.CAMERA -> android.Manifest.permission.CAMERA
        Permission.LOCATION -> android.Manifest.permission.ACCESS_FINE_LOCATION
        Permission.MICROPHONE -> android.Manifest.permission.RECORD_AUDIO
        Permission.GALLERY -> android.Manifest.permission.READ_MEDIA_IMAGES
        Permission.NOTIFICATION -> android.Manifest.permission.POST_NOTIFICATIONS
    }
}