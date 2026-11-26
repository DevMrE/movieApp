package com.kmp.movieapp.permission.di

import com.kmp.movieapp.permission.PermissionDemoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val permissionModule = module {
    viewModelOf(::PermissionDemoViewModel)
}