package com.kmp.movieapp.app.bottombar

import androidx.lifecycle.ViewModel
import com.kmp.kmpnavigation.util.NavDestination
import com.kmp.kmpnavigation.util.Navigation
import com.kmp.movieapp.app.bottombar.model.BottomBarItem
import com.kmp.movieapp.homescreen.destination.HomeScreenDestination
import com.kmp.movieapp.settings.destination.SettingsScreenDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie
import movieapp.composeapp.generated.resources.settings

class BottomBarViewModel(
    private val navigation: Navigation
) : ViewModel() {

    private val _currentScreen = MutableStateFlow(
        listOf(
            BottomBarItem(
                icon = Res.drawable.movie,
                navDestination = HomeScreenDestination
            ),
            BottomBarItem(
                icon = Res.drawable.settings,
                navDestination = SettingsScreenDestination
            ),
        )
    )

    val currentScreen = _currentScreen.asStateFlow()


    fun onScreenChanged(navDestination: NavDestination) {
        with(navigation) {
            switchTab(navDestination)
        }
    }
}