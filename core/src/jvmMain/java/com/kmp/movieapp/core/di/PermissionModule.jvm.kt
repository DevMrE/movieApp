package com.kmp.movieapp.core.di

import org.koin.core.module.Module
import org.koin.dsl.module

internal actual class SharedModuleHelper {

    actual val sharedCoreModule: Module
        get() = module { }
}