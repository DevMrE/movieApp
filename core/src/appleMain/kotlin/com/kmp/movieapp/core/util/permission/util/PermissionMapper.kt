package com.kmp.movieapp.core.util.permission.util

import com.kmp.movieapp.core.util.permission.PermissionState
import platform.AVFoundation.AVAuthorizationStatus
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusRestricted

internal fun AVAuthorizationStatus.toPermissionState() = when (this) {
    AVAuthorizationStatusAuthorized -> PermissionState.GRANTED
    AVAuthorizationStatusDenied,
    AVAuthorizationStatusRestricted -> PermissionState.PERMANENTLY_DENIED
    else -> PermissionState.NOT_DETERMINED
}