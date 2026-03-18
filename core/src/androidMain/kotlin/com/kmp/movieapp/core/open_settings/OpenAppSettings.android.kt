package com.kmp.movieapp.core.open_settings

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import co.touchlab.kermit.Logger
import com.kmp.movieapp.core.util.ActivityProvider

actual class SettingsNavigator {

    actual fun openAppSettings() {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", ActivityProvider.activity?.packageName, null)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            Logger.i(
                tag = "SettingsNavigator",
                messageString = "activity?: ${ActivityProvider.activity}"
            )

            ActivityProvider.activity?.startActivity(intent)
        } catch (e: Exception) {
            Logger.e(messageString = "Try to open settings", throwable = e.cause)
        }
    }
}