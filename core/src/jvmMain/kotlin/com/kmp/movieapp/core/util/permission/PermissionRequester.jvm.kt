package com.kmp.movieapp.core.util.permission

actual class PermissionRequester {
    actual suspend fun checkPermission(permission: Permission): PermissionState =
        PermissionState.GRANTED

    actual suspend fun requestPermission(permission: Permission): PermissionState =
        PermissionState.GRANTED
}