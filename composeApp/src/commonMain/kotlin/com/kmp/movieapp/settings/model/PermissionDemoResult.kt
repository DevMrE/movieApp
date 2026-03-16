package com.kmp.movieapp.settings.model

import com.kmp.movieapp.core.util.permission.Permission

sealed interface PermissionDemoResult {
    data object CameraReady : PermissionDemoResult
    data class LocationReady(val lat: Double?, val lng: Double?) : PermissionDemoResult
    data object MicrophoneReady : PermissionDemoResult
    data object NotificationReady : PermissionDemoResult
    data object GalleryReady : PermissionDemoResult
    data class PermissionDenied(val permission: Permission) : PermissionDemoResult
    data class PermissionPermanentlyDenied(val permission: Permission) : PermissionDemoResult
}