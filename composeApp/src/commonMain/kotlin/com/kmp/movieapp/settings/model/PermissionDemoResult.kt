package com.kmp.movieapp.settings.model

import com.kmp.movieapp.device_operations.domain.model.Media

sealed interface PermissionDemoResult {
    data object CameraReady : PermissionDemoResult
    data class LocationReady(val latitude: Double?, val longitude: Double?) : PermissionDemoResult
    data object MicrophoneReady : PermissionDemoResult
    data class GalleryReady(val mediaList: List<Media>) : PermissionDemoResult
    data class PermissionDenied(val permission: Permission) : PermissionDemoResult
    data class PermissionPermanentlyDenied(val permission: Permission) : PermissionDemoResult
}