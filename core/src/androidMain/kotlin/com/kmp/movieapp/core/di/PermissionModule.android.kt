package com.kmp.movieapp.core.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.kmp.movieapp.core.util.permission.PermissionRequester
import com.kmp.movieapp.core.util.permission.location.LocationProvider
import com.kmp.movieapp.core.util.permission.speech.SpeechRecognizer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedCoreModule: Module = module {
    factory { PermissionRequester(get<ComponentActivity>()) }

    factory { SpeechRecognizer(get<Context>()) }

    factory { LocationProvider(get<Context>()) }
}