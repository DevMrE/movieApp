package com.kmp.movieapp.device_operations.data.provider

import com.kmp.movieapp.device_operations.data.util.createOneShotFlow
import com.kmp.movieapp.device_operations.domain.model.Media
import com.kmp.movieapp.device_operations.domain.provider.CameraProvider
import com.kmp.movieapp.device_operations.platform.IOSDeviceOperationsBinder
import kotlinx.coroutines.flow.Flow
import platform.Foundation.NSURL
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerCameraCaptureMode
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerImageURL
import platform.UIKit.UIImagePickerControllerMediaURL
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject

/**
 * iOS implementation of [CameraProvider].
 *
 * Uses UIImagePickerController to capture a photo.
 * Expects camera permission to be granted before use.
 */
internal class IOSCameraProvider : CameraProvider {

    private var delegate: NSObject? = null

    /**
     * Opens the system camera and returns the captured photo.
     */
    override fun capturePhoto(): Flow<Media> = createOneShotFlow(
        block = { send ->
            val viewController = IOSDeviceOperationsBinder.getViewController() ?: return@createOneShotFlow

            val picker = UIImagePickerController().apply {
                sourceType =
                    UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
                cameraCaptureMode =
                    UIImagePickerControllerCameraCaptureMode.UIImagePickerControllerCameraCaptureModePhoto
            }

            val pickerDelegate = object : NSObject(),
                UIImagePickerControllerDelegateProtocol,
                UINavigationControllerDelegateProtocol {

                override fun imagePickerControllerDidCancel(
                    picker: UIImagePickerController
                ) {
                    picker.dismissViewControllerAnimated(
                        flag = true,
                        completion = null
                    )
                }

                override fun imagePickerController(
                    picker: UIImagePickerController,
                    didFinishPickingMediaWithInfo: Map<Any?, *>
                ) {
                    val imageUrl =
                        didFinishPickingMediaWithInfo[UIImagePickerControllerImageURL] as? NSURL

                    val mediaUrl =
                        didFinishPickingMediaWithInfo[UIImagePickerControllerMediaURL] as? NSURL

                    val uri = imageUrl?.absoluteString ?: mediaUrl?.absoluteString

                    picker.dismissViewControllerAnimated(
                        flag = true,
                        completion = null
                    )

                    uri?.let {
                        send(Media(uri = it))
                    }
                }
            }

            delegate = pickerDelegate
            picker.delegate = pickerDelegate

            viewController.presentViewController(
                viewControllerToPresent = picker,
                animated = true,
                completion = null
            )
        },
        onClose = {
            delegate = null
        }
    )
}