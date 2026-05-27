package com.kmp.movieapp.device_operations.data.provider

import android.app.Service
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.CameraProvider
import kotlinx.coroutines.flow.Flow

/**
 * Android implementation of [CameraProvider].
 *
 * Uses the system camera via Activity Result API and stores the image
 * in the MediaStore.
 */
internal class AndroidCameraProvider(
    private val context: Context
) : CameraProvider, Service() {

    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>

    private var callback: ((Boolean) -> Unit)? = null

    private var currentUri: Uri? = null

    private var isRegistered: Boolean = false

    /**
     * Registers the camera launcher.
     *
     * Must be called from Activity `onCreate`.
     */
    fun register(activity: ComponentActivity) {
        if (isRegistered) return

        takePictureLauncher = activity.registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            callback?.invoke(success)
            callback = null
        }

        isRegistered = true
    }

    /**
     * Opens the system camera and returns the captured photo.
     */
    override fun capturePhoto(): Flow<Media> = createOneShotFlow(
        block = { send ->

            val uri = createImageUri()
            currentUri = uri

            callback = { success ->
                if (success) {
                    uri?.let {
                        send(
                            Media(
                                uri = it.toString()
                            )
                        )
                    }
                }
            }

            uri?.let { takePictureLauncher.launch(it) }
        }
    )

    /**
     * Creates a MediaStore entry for the captured image.
     */
    private fun createImageUri(): Uri? {
        val resolver = context.contentResolver

        val contentValues = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "IMG_${System.currentTimeMillis()}.jpg"
            )
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        return resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}