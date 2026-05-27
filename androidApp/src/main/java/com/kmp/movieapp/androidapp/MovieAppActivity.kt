package com.kmp.movieapp.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.components.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.device_operations.platform.AndroidDeviceOperationsBinder
import org.koin.android.ext.android.inject

class MovieAppActivity : ComponentActivity() {
    private val androidDeviceOperationsBinder: AndroidDeviceOperationsBinder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        androidDeviceOperationsBinder.bind(this)

        setContent {
            MobileAppScreen()
        }
    }

    override fun onDestroy() {
        androidDeviceOperationsBinder.unbind(this)
        super.onDestroy()
    }
}
