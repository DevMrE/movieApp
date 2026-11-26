package com.kmp.movieapp.profile

import com.kmp.movieapp.profile.model.Permission

internal interface ProfileAction {

    data class OnPermissionClicked(val permission: Permission) : ProfileAction
}