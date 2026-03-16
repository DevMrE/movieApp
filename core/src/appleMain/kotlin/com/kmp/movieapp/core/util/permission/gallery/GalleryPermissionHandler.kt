package com.kmp.movieapp.core.util.permission.gallery

import com.kmp.movieapp.core.util.permission.PermissionState
import com.kmp.movieapp.core.util.permission.util.PermissionHandler
import com.kmp.movieapp.core.util.permission.util.suspendPermission
import platform.Photos.PHAccessLevelReadWrite
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusDenied
import platform.Photos.PHAuthorizationStatusLimited
import platform.Photos.PHAuthorizationStatusRestricted
import platform.Photos.PHPhotoLibrary

internal class GalleryPermissionHandler : PermissionHandler {

    override suspend fun check() = when (
        PHPhotoLibrary.authorizationStatusForAccessLevel(PHAccessLevelReadWrite)
    ) {
        PHAuthorizationStatusAuthorized,
        PHAuthorizationStatusLimited -> PermissionState.GRANTED
        PHAuthorizationStatusDenied,
        PHAuthorizationStatusRestricted -> PermissionState.PERMANENTLY_DENIED
        else -> PermissionState.NOT_DETERMINED
    }

    override suspend fun request() = suspendPermission<PermissionState> { callback ->
        PHPhotoLibrary.requestAuthorizationForAccessLevel(PHAccessLevelReadWrite) { status ->
            callback(when (status) {
                PHAuthorizationStatusAuthorized,
                PHAuthorizationStatusLimited -> PermissionState.GRANTED
                PHAuthorizationStatusDenied,
                PHAuthorizationStatusRestricted -> PermissionState.PERMANENTLY_DENIED
                else -> PermissionState.DENIED
            })
        }
    }
}