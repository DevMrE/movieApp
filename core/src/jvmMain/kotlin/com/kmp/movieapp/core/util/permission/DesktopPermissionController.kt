package com.kmp.movieapp.core.util.permission

import com.kmp.movieapp.core.permission.domain.Permission
import com.kmp.movieapp.core.permission.PermissionRequest
import com.kmp.movieapp.core.permission.domain.PermissionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

/**
 * Desktop stub implementation of [com.kmp.movieapp.core.permission.PermissionRequest].
 *
 * Returns [PermissionStatus.Granted] for all permissions.
 * Desktop platforms do not use the same runtime permission model as mobile.
 *
 * Replace individual branches with real implementations as desktop support matures —
 * the [com.kmp.movieapp.core.permission.PermissionRequest] interface guarantees zero impact on [commonMain] consumers.
 */
class DesktopPermissionControllers : PermissionRequest {

    private val states = MutableStateFlow<Map<Permission, PermissionStatus>>(emptyMap())

    override fun observePermissionState(permission: Permission): Flow<PermissionStatus> =
        flowOf(PermissionStatus.GRANTED)

    override suspend fun requestPermission(permission: Permission) {
        states.update { it + (permission to PermissionStatus.GRANTED) }
    }
}