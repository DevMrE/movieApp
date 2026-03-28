package com.kmp.movieapp

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.device_operations.platform.IOSDeviceOperationsBinder
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    val viewController = ComposeUIViewController {
        MobileAppScreen()
    }

    IOSDeviceOperationsBinder.bind(viewController)

    return viewController
}