package com.kmp.movieapp.app_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kmp.movieapp.app_screen.mobile.MobileAppScreen
import com.kmp.movieapp.navigation.registerAppNavigation

class MovieAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        registerAppNavigation()

        setContent {
            MobileAppScreen()
        }
    }
}
