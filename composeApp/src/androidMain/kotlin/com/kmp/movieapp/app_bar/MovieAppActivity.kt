package com.kmp.movieapp.app_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.core.permission.domain.AndroidPermissionBinder
import com.kmp.movieapp.navigation.registerAppNavigation
import org.koin.android.ext.android.inject

class MovieAppActivity : ComponentActivity() {
    private val androidPermissionBinder: AndroidPermissionBinder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        androidPermissionBinder.bind(this)

        registerAppNavigation()

        setContent {
            MobileAppScreen()
        }
    }

    override fun onDestroy() {
        androidPermissionBinder.unbind(this)
        super.onDestroy()
    }
}
