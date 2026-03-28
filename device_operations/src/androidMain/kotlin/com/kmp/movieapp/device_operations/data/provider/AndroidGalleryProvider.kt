package com.kmp.movieapp.device_operations.data.provider

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import kotlinx.coroutines.flow.Flow

/**
 * Android implementation of [GalleryProvider].
 *
 * Uses the system Photo Picker to select images.
 * This provider must be registered from the hosting Activity before use.
 */
internal class AndroidGalleryProvider : GalleryProvider {

    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>

    private var callback: ((List<Uri>) -> Unit)? = null

    private var isRegistered: Boolean = false

    /**
     * Registers the internal Photo Picker launcher.
     *
     * This function must be called from `onCreate`.
     */
    fun register(activity: ComponentActivity) {
        if (isRegistered) return

        pickMultipleMediaLauncher = activity.registerForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia()
        ) { uris ->
            callback?.invoke(uris)
            callback = null
        }

        isRegistered = true
    }

    /**
     * Opens the system image picker and returns the selected media list.
     */
    override fun pickImages(): Flow<List<Media>> = createOneShotFlow(
        block = { send ->
            callback = { uris ->
                send(
                    uris.map { uri ->
                        Media(uri = uri.toString())
                    }
                )
            }

            pickMultipleMediaLauncher.launch(
                input = PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
    )
}