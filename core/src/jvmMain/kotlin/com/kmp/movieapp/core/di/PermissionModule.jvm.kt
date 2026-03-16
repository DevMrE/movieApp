package com.kmp.movieapp.core.di

import com.kmp.movieapp.core.util.permission.PermissionRequester
import com.kmp.movieapp.core.util.permission.location.LocationProvider
import com.kmp.movieapp.core.util.permission.speech.SpeechRecognizer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {
    factory<PermissionRequester> { PermissionRequester() }
    factory<SpeechRecognizer> { SpeechRecognizer()}
    factory<LocationProvider> { LocationProvider() }
}