package com.kmp.movieapp.device_operations.data.provider

import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.GalleryProvider
import com.kmp.movieapp.device_operations.platform.IOSDeviceOperationsBinder
import kotlinx.coroutines.flow.Flow
import platform.Foundation.NSURL
import platform.Photos.PHPhotoLibrary
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.darwin.NSObject

/**
 * iOS implementation of [GalleryProvider].
 *
 * Uses PHPickerViewController to select images without requiring
 * a separate gallery permission flow.
 */
internal class IOSGalleryProvider(
    private val iosDeviceOperationsBinder: IOSDeviceOperationsBinder
) : GalleryProvider {

    private var pickerDelegate: PHPickerViewControllerDelegateProtocol? = null

    /**
     * Opens the system image picker and returns the selected media.
     */
    override fun pickImages(): Flow<List<Media>> = createOneShotFlow(
        block = { send ->
            val viewController = iosDeviceOperationsBinder.getViewController()
            if (viewController == null) {
                send(emptyList())
                return@createOneShotFlow
            }

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
                        picker.dismissViewControllerAnimated(true, completion = null)
                        send(emptyList())
                        return
                    }

                    val mediaList = mutableListOf<Media>()
                    var pendingCount = results.size

                    results.forEach { result ->
                        val itemProvider = result.itemProvider
                        val typeIdentifier = itemProvider.registeredTypeIdentifiers.firstOrNull() as? String

                        if (typeIdentifier == null) {
                            pendingCount -= 1
                            if (pendingCount == 0) {
                                picker.dismissViewControllerAnimated(true, completion = null)
                                send(mediaList)
                            }
                            return@forEach
                        }

                        itemProvider.loadFileRepresentationForTypeIdentifier(
                            typeIdentifier = typeIdentifier
                        ) { url: NSURL?, _ ->
                            url?.absoluteString?.let { uri ->
                                mediaList += Media(uri = uri)
                            }

                            pendingCount -= 1
                            if (pendingCount == 0) {
                                picker.dismissViewControllerAnimated(true, completion = null)
                                send(mediaList)
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
}