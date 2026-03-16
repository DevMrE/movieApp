package com.kmp.movieapp.settings

import com.kmp.movieapp.core.util.permission.Permission

internal interface SettingsAction {

    data class OnPermissionClicked(val permission: Permission) : SettingsAction
}