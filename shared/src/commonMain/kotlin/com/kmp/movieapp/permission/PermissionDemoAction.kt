package com.kmp.movieapp.permission

import com.kmp.movieapp.permission.model.Permission

internal interface PermissionDemoAction {

    data class OnPermissionClicked(val permission: Permission) : PermissionDemoAction
}