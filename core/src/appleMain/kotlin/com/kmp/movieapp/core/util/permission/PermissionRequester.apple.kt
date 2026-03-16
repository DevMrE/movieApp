package com.kmp.movieapp.core.util.permission

import com.kmp.movieapp.core.util.permission.gallery.GalleryPermissionHandler
import com.kmp.movieapp.core.util.permission.location.LocationPermissionHandler
import com.kmp.movieapp.core.util.permission.media.AVMediaPermissionHandler
import com.kmp.movieapp.core.util.permission.notification.NotificationPermissionHandler
import com.kmp.movieapp.core.util.permission.util.PermissionHandler
import platform.AVFoundation.AVMediaTypeAudio
import platform.AVFoundation.AVMediaTypeVideo

actual class PermissionRequester {

    actual suspend fun checkPermission(permission: Permission): PermissionState =
        permission.handler().check()

    actual suspend fun requestPermission(permission: Permission): PermissionState =
        permission.handler().request()

    private fun Permission.handler(): PermissionHandler = when (this) {
        Permission.CAMERA -> AVMediaPermissionHandler(AVMediaTypeVideo)
        Permission.MICROPHONE -> AVMediaPermissionHandler(AVMediaTypeAudio)
        Permission.LOCATION -> LocationPermissionHandler()
        Permission.GALLERY -> GalleryPermissionHandler()
        Permission.NOTIFICATION -> NotificationPermissionHandler()
    }
}