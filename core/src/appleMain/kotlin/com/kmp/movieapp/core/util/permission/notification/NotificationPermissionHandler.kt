package com.kmp.movieapp.core.util.permission.notification

import com.kmp.movieapp.core.util.permission.PermissionState
import com.kmp.movieapp.core.util.permission.util.PermissionHandler
import com.kmp.movieapp.core.util.permission.util.suspendPermission
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNAuthorizationStatusDenied
import platform.UserNotifications.UNAuthorizationStatusProvisional
import platform.UserNotifications.UNUserNotificationCenter

internal class NotificationPermissionHandler : PermissionHandler {

    override suspend fun check() = suspendPermission { callback ->
        UNUserNotificationCenter.currentNotificationCenter()
            .getNotificationSettingsWithCompletionHandler { settings ->
                callback(when (settings?.authorizationStatus) {
                    UNAuthorizationStatusAuthorized,
                    UNAuthorizationStatusProvisional -> PermissionState.GRANTED
                    UNAuthorizationStatusDenied -> PermissionState.PERMANENTLY_DENIED
                    else -> PermissionState.NOT_DETERMINED
                })
            }
    }

    override suspend fun request() = suspendPermission { callback ->
        UNUserNotificationCenter.currentNotificationCenter()
            .requestAuthorizationWithOptions(
                UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
            ) { granted, _ ->
                callback(if (granted) PermissionState.GRANTED else PermissionState.DENIED)
            }
    }
}