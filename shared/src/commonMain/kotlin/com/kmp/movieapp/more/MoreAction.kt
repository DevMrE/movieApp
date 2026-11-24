package com.kmp.movieapp.more

import com.kmp.movieapp.more.model.Permission

internal interface MoreAction {

    data class OnPermissionClicked(val permission: Permission) : MoreAction
}