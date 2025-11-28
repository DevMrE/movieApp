package com.kmp.movieapp.app.bottombar

import androidx.lifecycle.ViewModel
import com.kmp.navigation.NavDestination
import com.kmp.navigation.Navigation

class BottomBarViewModel(
    private val navigation: Navigation
) : ViewModel() {

    fun onScreenChanged(navDestination: NavDestination) {
        with(navigation) {
            switchTab(navDestination)
        }
    }
}