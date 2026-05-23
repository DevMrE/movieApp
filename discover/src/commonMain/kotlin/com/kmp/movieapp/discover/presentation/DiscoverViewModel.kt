package com.kmp.movieapp.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import com.kmp.movieapp.core.util.navigation.route.HomeNavigation
import com.kmp.movieapp.core.util.viewmodel.stateInLazily
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.discover.presentation.mapper.toUiDiscoverList
import com.kmp.movieapp.discover.presentation.model.UiDiscover
import com.kmp.movieapp.discover.presentation.model.UiFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val navigator: Navigator<Route>
) : ViewModel() {

    private val _discoverState = MutableStateFlow<UiDiscover?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val discoverState: StateFlow<UiDiscover?> = _discoverState
        .flatMapLatest {
            getDiscoverUseCase(page = _discoverState.value?.page ?: 1)
        }.map { data ->
            _discoverState.value ?: _discoverState.updateAndGet {
                data.toUiDiscoverList()
            }
        }
        .stateInLazily(_discoverState.value)

    fun onAction(action: DiscoverAction) {
        when (action) {
            is DiscoverAction.OnFilterClicked -> onUpdateFilter(action.uiFilter)
            is DiscoverAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
        }
    }

    private fun onLoadNextPage() {
        viewModelScope.launch {
            _discoverState.update {
                it?.copy(
                    page = it.page + 1
                )
            }
        }
    }

    private fun onUpdateFilter(uiFilter: UiFilter) {
        viewModelScope.launch {
            _discoverState.update { state ->
                state?.copy(
                    filter = state.filter
                        .filter { it == uiFilter }
                        .map { filter ->
                            filter.copy(isSeclected = !filter.isSeclected)
                        }
                )
            }
        }
    }


    private fun navigateToContentDetail(uiMediaCard: UiMediaCard) {
        logI(message = "Navigate with id: $uiMediaCard")
        navigator.navigateTo(
            route = HomeNavigation.ContentDetail(
                id = uiMediaCard.id,
                mediaCategory = uiMediaCard.type
            )
        )
    }

}