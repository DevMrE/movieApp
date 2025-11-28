package com.kmp.movieapp.app.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import com.kmp.navigation.compose.rememberNavDestination
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie
import movieapp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BottomBarComponent() {
    val viewModel = koinViewModel<BottomBarViewModel>()
    val navDestination = rememberNavDestination(initialDestination = HomeScreenDestination)

    NavigationBar {
        NavigationBarItem(
            selected = navDestination != SettingsScreenDestination,
            onClick = { viewModel.onScreenChanged(navDestination = HomeScreenDestination) },
            icon = {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.movie),
                    contentDescription = null
                )
            },
        )

        NavigationBarItem(
            selected = navDestination == SettingsScreenDestination,
            onClick = { viewModel.onScreenChanged(navDestination = SettingsScreenDestination) },
            icon = {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.settings),
                    contentDescription = null
                )
            },
        )
    }
}