package com.kmp.movieapp.app.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BottomBarComponent() {
    val viewModel = koinViewModel<BottomBarViewModel>()
    val list by viewModel.currentScreen.collectAsStateWithLifecycle()

    NavigationBar {
        list.forEach { bottomBarItem ->
            NavigationBarItem(
                selected = bottomBarItem.navDestination == HomeScreenDestination,
                onClick = { viewModel.onScreenChanged(bottomBarItem.navDestination) },
                icon = { Icon(vectorResource(bottomBarItem.icon), contentDescription = null) },
            )
        }
    }
}