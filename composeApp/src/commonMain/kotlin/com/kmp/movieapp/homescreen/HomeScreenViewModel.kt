package com.kmp.movieapp.homescreen

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.homescreen.model.UiTabState
import com.kmp.movieapp.movie.presentation.route.MovieScreenDestination
import com.kmp.navigation.destination.SeriesScreenDestination
import com.kmp.navigation.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import movieapp.composeapp.generated.resources.Res
import movieapp.composeapp.generated.resources.movie_tab
import movieapp.composeapp.generated.resources.series_tab

class HomeScreenViewModel(
    private val navigation: Navigation
) : ViewModel() {

    private val _tabState = MutableStateFlow(
        value = UiTabState(
            movieTabResource = Res.string.movie_tab,
            seriesTabResource = Res.string.series_tab,
            selectedTab = MovieScreenDestination
        ),
    )
    val tabState = _tabState.asStateFlow()

    fun onTabChanged() {
        val destination = if (_tabState.value.selectedTab == MovieScreenDestination) {
            SeriesScreenDestination
        } else MovieScreenDestination

        _tabState.update { it.copy(selectedTab = destination) }
        navigation.switchTab(destination)
    }
}