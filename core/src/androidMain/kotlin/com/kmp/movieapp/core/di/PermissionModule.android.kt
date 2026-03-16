package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.util.ActivityProvider
import com.kmp.movieapp.core.util.permission.PermissionRequester
import com.kmp.movieapp.core.util.permission.location.LocationProvider
import com.kmp.movieapp.core.util.permission.speech.SpeechRecognizer
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {
    factory {
        PermissionRequester(ActivityProvider.activity ?: error("No Activity available"))
    }

    factory { SpeechRecognizer(androidContext()) }
    factory { LocationProvider(androidContext()) }
}