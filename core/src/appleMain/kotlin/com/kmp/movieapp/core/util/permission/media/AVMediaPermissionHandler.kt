package com.kmp.movieapp.core.util.permission.media

import com.kmp.movieapp.core.util.permission.PermissionState
import com.kmp.movieapp.core.util.permission.util.PermissionHandler
import com.kmp.movieapp.core.util.permission.util.suspendPermission
import com.kmp.movieapp.core.util.permission.util.toPermissionState
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType

internal class AVMediaPermissionHandler(private val mediaType: String?) : PermissionHandler {

    override suspend fun check() =
        AVCaptureDevice.authorizationStatusForMediaType(mediaType).toPermissionState()

    override suspend fun request() = suspendPermission { callback ->
        AVCaptureDevice.requestAccessForMediaType(mediaType) { granted ->
            callback(if (granted) PermissionState.GRANTED else PermissionState.DENIED)
        }
    }
}