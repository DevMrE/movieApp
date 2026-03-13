package com.kmp.movieapp.app_bar

import android.app.Application
import com.kmp.movieapp.di.initModules
import org.conscrypt.Conscrypt
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.security.Security

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Security.insertProviderAt(Conscrypt.newProvider(), 1)

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