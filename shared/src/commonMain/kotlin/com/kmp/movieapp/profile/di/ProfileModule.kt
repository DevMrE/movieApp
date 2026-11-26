package com.kmp.movieapp.profile.di

import com.kmp.movieapp.permission.di.permissionModule
import org.koin.dsl.module

val profileModule = module {
    includes(permissionModule)
}