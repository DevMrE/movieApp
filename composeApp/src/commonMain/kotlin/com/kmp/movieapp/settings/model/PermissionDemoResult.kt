package com.kmp.movieapp.settings.model

import com.kmp.movieapp.core.permission.domain.Permission

sealed interface PermissionDemoResult {
    data object CameraReady : PermissionDemoResult
    data class LocationReady(val latitude: Double?, val longitude: Double?) : PermissionDemoResult
    data object MicrophoneReady : PermissionDemoResult
    data object NotificationReady : PermissionDemoResult
    data object GalleryReady : PermissionDemoResult
    data class PermissionDenied(val permission: Permission) : PermissionDemoResult
    data class PermissionPermanentlyDenied(val permission: Permission) : PermissionDemoResult
}