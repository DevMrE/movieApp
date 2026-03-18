package com.kmp.movieapp.core.permission.domain

import com.kmp.movieapp.core.permission.domain.model.Media
import com.kmp.movieapp.core.permission.util.PermissionResult
import com.kmp.movieapp.core.permission.util.createPermissionFlow
import com.kmp.movieapp.core.util.tuples.with
import kotlinx.coroutines.flow.Flow
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionRecordPermissionDenied
import platform.AVFAudio.AVAudioSessionRecordPermissionGranted
import platform.AVFAudio.AVAudioSessionRecordPermissionUndetermined
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusDenied
import platform.Photos.PHAuthorizationStatusNotDetermined
import platform.Photos.PHPhotoLibrary

/**
 * Handles one-shot iOS permission requests that do not require a dedicated delegate
 * lifecycle like location does.
 */
object IOSPermissionHandler {

    /**
     * Requests camera permission.
     */
    fun requestCamera(): Flow<PermissionResult<Unit>> =
        createPermissionFlow { send ->

            when (AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)) {
                AVAuthorizationStatusAuthorized -> {
                    send(PermissionResult(PermissionStatus.GRANTED))
                }

                AVAuthorizationStatusDenied -> {
                    send(PermissionResult(PermissionStatus.DENIED))
                }

                AVAuthorizationStatusNotDetermined -> {
                    AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) { granted ->
                        send(
                            PermissionResult(
                                if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
                            )
                        )
                    }
                }

                else -> {
                    send(PermissionResult(PermissionStatus.DENIED))
                }
            }
        }

    /**
     * Requests microphone permission.
     */
    fun requestMicrophone(): Flow<PermissionResult<Unit>> =
        createPermissionFlow { send ->

            val session = AVAudioSession.sharedInstance()

            when (session.recordPermission()) {
                AVAudioSessionRecordPermissionGranted -> {
                    send(PermissionResult(PermissionStatus.GRANTED))
                }

                AVAudioSessionRecordPermissionDenied -> {
                    send(PermissionResult(PermissionStatus.DENIED))
                }

                AVAudioSessionRecordPermissionUndetermined -> {
                    session.requestRecordPermission { granted ->
                        send(
                            PermissionResult(
                                if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
                            )
                        )
                    }
                }

                else -> send(PermissionResult(PermissionStatus.DENIED))
            }
        }

    /**
     * Requests gallery/photo library permission.
     *
     * This handler only reports permission state. Actual picker results can be
     * resolved by a dedicated picker component.
     */
    fun requestGallery(): Flow<PermissionResult<List<Media>>> =
        createPermissionFlow { send ->

            when (PHPhotoLibrary.authorizationStatus()) {
                PHAuthorizationStatusAuthorized -> {
                    send(
                        PermissionResult(
                            status = PermissionStatus.GRANTED,
                            data = emptyList()
                        )
                    )
                }

                PHAuthorizationStatusDenied -> {
                    send(PermissionResult(PermissionStatus.DENIED))
                }

                PHAuthorizationStatusNotDetermined -> {
                    PHPhotoLibrary.requestAuthorization { newStatus ->
                        val (status, data) =
                            if (newStatus == PHAuthorizationStatusAuthorized) {
                                PermissionStatus.GRANTED with emptyList<Media>()
                            } else PermissionStatus.DENIED with null

                        val permissionResult = PermissionResult(
                            status = status,
                            data = data
                        )
                        send(permissionResult)
                    }
                }

                else -> send(PermissionResult(PermissionStatus.DENIED))
            }
        }
}