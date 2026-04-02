package com.kmp.movieapp.homescreen

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.composeApp.Res
import com.kmp.movieapp.composeApp.movie_tab
import com.kmp.movieapp.composeApp.series_tab
import com.kmp.movieapp.homescreen.model.UiTabState
import com.kmp.navigation.NavDestination
import com.kmp.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(
    private val navigation: Navigation,
) : ViewModel() {

    private val _tabState = MutableStateFlow(
        value = UiTabState(
            movieTabResource = Res.string.movie_tab,
            seriesTabResource = Res.string.series_tab
        ),
    )
    val tabState = _tabState.asStateFlow()

    fun onTabChanged(navDestination: NavDestination) {
        navigation.navigateTo(navDestination)
    }
}