package com.kmp.movieapp.core.open_settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.kmp.movieapp.core.util.logger.logE

class AndroidSettingsNavigator(
    private val context: Context
) : SettingsNavigator {

    override fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        runCatching {
            context.startActivity(intent)
        }.onFailure {
            logE<SettingsNavigator>("Something went wrong by opening the settings: $it")
        }
    }
}