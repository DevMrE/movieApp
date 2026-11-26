package com.kmp.movieapp.androidapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.components.app_screen.PlatformScreen
import com.kmp.movieapp.device_operations.platform.AndroidDeviceOperationsBinder
import org.koin.android.ext.android.inject

class MovieAppActivity : ComponentActivity() {
    private val androidDeviceOperationsBinder: AndroidDeviceOperationsBinder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        applyOrientationPolicy()

        androidDeviceOperationsBinder.bind(this)

        setContent {
            PlatformScreen()
        }
    }

    override fun onDestroy() {
        androidDeviceOperationsBinder.unbind(this)
        super.onDestroy()
    }

    private fun applyOrientationPolicy() {
        // Phones (< 600dp smallest width) are locked to portrait, tablets rotate freely
        val isTablet = resources.configuration.smallestScreenWidthDp >= 600
        requestedOrientation = if (isTablet) {
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}
