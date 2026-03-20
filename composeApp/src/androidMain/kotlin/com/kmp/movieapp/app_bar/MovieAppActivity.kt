package com.kmp.movieapp.app_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.device_operations.platform.AndroidDeviceOperationsBinder
import com.kmp.movieapp.navigation.registerAppNavigation
import org.koin.android.ext.android.inject

class MovieAppActivity : ComponentActivity() {
    private val androidDeviceOperationsBinder: AndroidDeviceOperationsBinder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        androidDeviceOperationsBinder.bind(this)

        registerAppNavigation()

        setContent {
            MobileAppScreen()
        }
    }

    override fun onDestroy() {
        androidDeviceOperationsBinder.unbind(this)
        super.onDestroy()
    }
}
