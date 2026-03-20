package com.kmp.movieapp.settings

import com.kmp.movieapp.settings.model.Permission

internal interface SettingsAction {

    data class OnPermissionClicked(val permission: Permission) : SettingsAction
}