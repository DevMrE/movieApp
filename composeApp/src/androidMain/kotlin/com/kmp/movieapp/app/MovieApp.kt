package com.kmp.movieapp.app

import android.app.Application
import com.kmp.movieapp.di.initModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidLogger(Level.DEBUG)
            androidLogger(Level.ERROR)
            androidLogger(Level.WARNING)
            androidContext(this@MovieApp)
            initModules()
        }
    }
}