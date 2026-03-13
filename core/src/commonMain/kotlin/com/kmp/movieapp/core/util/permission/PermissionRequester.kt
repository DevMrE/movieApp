package com.kmp.movieapp.core.util.permission

interface PermissionRequester {
    suspend fun requestPermission(permission: PermissionType): Boolean
}
