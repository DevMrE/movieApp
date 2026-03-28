package com.kmp.movieapp.device_operations.data.provider

import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import com.kmp.movieapp.device_operations.platform.IOSDeviceOperationsBinder
import kotlinx.coroutines.flow.Flow
import platform.Foundation.NSData
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.writeToURL
import platform.Photos.PHPhotoLibrary
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

/**
 * iOS implementation of [GalleryProvider].
 *
 * Uses PHPickerViewController to select images without requiring
 * a separate gallery permission flow.
 */
internal class IOSGalleryProvider : GalleryProvider {

    private var pickerDelegate: PHPickerViewControllerDelegateProtocol? = null

    /**
     * Opens the system image picker and returns selected media.
     */
    override fun pickImages(): Flow<List<Media>> = createOneShotFlow(
        block = { send ->
            val viewController = IOSDeviceOperationsBinder.getViewController() ?: return@createOneShotFlow

            val configuration = PHPickerConfiguration(
                photoLibrary = PHPhotoLibrary.sharedPhotoLibrary()
            ).apply {
                filter = PHPickerFilter.imagesFilter()
                selectionLimit = 0
            }

            val pickerViewController = PHPickerViewController(configuration = configuration)

            pickerDelegate = object : NSObject(), PHPickerViewControllerDelegateProtocol {
                override fun picker(
                    picker: PHPickerViewController,
                    didFinishPicking: List<*>
                ) {
                    val results = didFinishPicking.filterIsInstance<PHPickerResult>()

                    if (results.isEmpty()) {
                        picker.dismissViewControllerAnimated(
                            flag = true,
                            completion = null
                        )
                        send(emptyList())
                        return
                    }

                    val mediaList = mutableListOf<Media>()
                    var remainingCount = results.size

                    fun finishOne() {
                        remainingCount -= 1
                        if (remainingCount == 0) {
                            picker.dismissViewControllerAnimated(
                                flag = true,
                                completion = null
                            )
                            send(mediaList.toList())
                        }
                    }

                    results.forEach { result ->
                        val itemProvider = result.itemProvider
                        val typeIdentifier =
                            itemProvider.registeredTypeIdentifiers.firstOrNull() as? String

                        if (typeIdentifier == null) {
                            dispatch_async(dispatch_get_main_queue()) {
                                finishOne()
                            }
                            return@forEach
                        }

                        itemProvider.loadDataRepresentationForTypeIdentifier(
                            typeIdentifier = typeIdentifier
                        ) { data: NSData?, _ ->
                            dispatch_async(dispatch_get_main_queue()) {
                                if (data != null) {
                                    persistDataToTemporaryFile(
                                        data = data,
                                        typeIdentifier = typeIdentifier
                                    )?.absoluteString?.let { uri ->
                                        mediaList += Media(
                                            uri = uri,
                                            mimeType = typeIdentifier
                                        )
                                    }
                                }

                                finishOne()
                            }
                        }
                    }
                }
            }

            pickerViewController.delegate = pickerDelegate
            viewController.presentViewController(
                viewControllerToPresent = pickerViewController,
                animated = true,
                completion = null
            )
        },
        onClose = {
            pickerDelegate = null
        }
    )

    /**
     * Persists the picked image data into a temporary file and returns its URL.
     */
    private fun persistDataToTemporaryFile(
        data: NSData,
        typeIdentifier: String
    ): NSURL? {
        val fileExtension = when {
            typeIdentifier.contains("png", ignoreCase = true) -> "png"
            typeIdentifier.contains("heic", ignoreCase = true) -> "heic"
            typeIdentifier.contains("jpeg", ignoreCase = true) -> "jpg"
            typeIdentifier.contains("jpg", ignoreCase = true) -> "jpg"
            else -> "tmp"
        }

        val fileName = "${NSUUID().UUIDString}.$fileExtension"
        val filePath = NSTemporaryDirectory() + fileName
        val fileUrl = NSURL.fileURLWithPath(filePath)

        val writeSucceeded = data.writeToURL(
            url = fileUrl,
            atomically = true
        )

        return fileUrl.takeIf { writeSucceeded }
    }
}