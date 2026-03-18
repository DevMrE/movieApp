package com.kmp.movieapp.core.permission.domain

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import com.kmp.movieapp.core.permission.util.createPermissionFlow
import kotlinx.coroutines.flow.Flow

/**
 * Registers and launches the Android gallery picker.
 *
 * This class is independent of runtime permission handling. It only exposes
 * the picker result as a cold flow.
 */
class AndroidGalleryProvider {

    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private var callback: ((List<String>) -> Unit)? = null
    private var isRegistered = false

    /**
     * Registers the gallery picker launcher.
     *
     * Must be called from the Activity during `onCreate`.
     */
    fun register(activity: ComponentActivity) {
        if (isRegistered) return

        galleryLauncher = activity.registerForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) { uris ->
            callback?.invoke(uris.map { it.toString() })
        }

        isRegistered = true
    }

    /**
     * Opens the gallery picker and returns the selected media.
     */
    fun openGallery(): Flow<PermissionResult<List<Media>>> = createPermissionFlow(
        awaitClose = { callback = null }
    ) { send ->
        callback = { uriStrings ->
            send(
                PermissionResult(
                    status = PermissionStatus.GRANTED,
                    data = uriStrings.map(::Media)
                )
            )
        }

        galleryLauncher.launch("image/*")
    }
}