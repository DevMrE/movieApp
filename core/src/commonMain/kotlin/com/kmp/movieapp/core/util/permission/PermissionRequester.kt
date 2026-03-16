package com.kmp.movieapp.core.util.permission

expect class PermissionRequester {
    internal suspend fun checkPermission(permission: Permission): PermissionState
    internal suspend fun requestPermission(permission: Permission): PermissionState
}

suspend fun PermissionRequester.require(
    permission: Permission,
    onGranted: suspend () -> Unit,
    onDenied: suspend (PermissionState) -> Unit = {}
) {
    when (val state = checkPermission(permission)) {
        PermissionState.GRANTED -> onGranted()
        PermissionState.NOT_DETERMINED -> {
            val result = requestPermission(permission)
            if (result == PermissionState.GRANTED) onGranted()
            else onDenied(result)
        }

        else -> onDenied(state)
    }
}