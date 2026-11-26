package com.kmp.movieapp

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.movieapp.components.app_screen.PlatformScreen
import com.kmp.movieapp.device_operations.platform.IOSDeviceOperationsBinder
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val uiViewController = ComposeUIViewController {
        PlatformScreen()
    }

    IOSDeviceOperationsBinder.bind(uiViewController)

    return uiViewController
}