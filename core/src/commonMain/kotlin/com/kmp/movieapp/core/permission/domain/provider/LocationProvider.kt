package com.kmp.movieapp.core.permission.domain.provider

import com.kmp.movieapp.core.permission.domain.model.Location
import com.kmp.movieapp.core.permission.util.PermissionResult
import kotlinx.coroutines.flow.Flow

interface LocationProvider {

    fun getLocation(): Flow<PermissionResult<Location>>
}