package com.kmp.movieapp.app_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.core.permission.domain.AndroidPermissionLauncher
import com.kmp.movieapp.core.permission.domain.AndroidPermissionsController
import com.kmp.movieapp.core.util.ActivityProvider
import com.kmp.movieapp.navigation.registerAppNavigation
import org.koin.android.ext.android.inject

class MovieAppActivity : ComponentActivity() {
    private val androidPermissionsController: AndroidPermissionsController by inject()
    private val androidPermissionLauncher: AndroidPermissionLauncher by lazy {
        AndroidPermissionLauncher(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        registerActivity(androidPermissionsController, androidPermissionLauncher)

        registerAppNavigation()

        setContent {
            MobileAppScreen()
        }
    }
}

private fun ComponentActivity.registerActivity(
    androidPermissionsController: AndroidPermissionsController,
    androidPermissionLauncher: AndroidPermissionLauncher
) {
    ActivityProvider.activity = this
    androidPermissionLauncher.register()
    androidPermissionsController.bindLauncher(androidPermissionLauncher)

}