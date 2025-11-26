package com.kmp.movieapp.homescreen

import androidx.lifecycle.ViewModel
import com.kmp.kmpnavigation.util.NavDestination
import com.kmp.kmpnavigation.util.Navigation
import com.kmp.movieapp.homescreen.model.UiTabState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie_tab
import movieapp.composeapp.generated.resources.series_tab

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