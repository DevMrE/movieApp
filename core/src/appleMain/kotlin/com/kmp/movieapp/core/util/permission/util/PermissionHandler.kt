package com.kmp.movieapp.core.util.permission.util

import com.kmp.movieapp.core.util.permission.PermissionState

internal interface PermissionHandler {
    suspend fun check(): PermissionState  // suspend hinzugefügt
    suspend fun request(): PermissionState
}