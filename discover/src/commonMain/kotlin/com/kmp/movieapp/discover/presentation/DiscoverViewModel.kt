package com.kmp.movieapp.discover.presentation

import androidx.lifecycle.ViewModel
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.discover.domain.model.Discover
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.discover.presentation.destination.ContentDetailDestination
import com.kmp.movieapp.discover.presentation.mapper.toUiDiscoverList
import com.kmp.movieapp.discover.presentation.model.UiDiscover
import com.kmp.movieapp.discover.presentation.model.UiFilter
import com.kmp.navigation.Navigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class DiscoverViewModel(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val navigation: Navigation
) : ViewModel() {


    private val _discoverState = MutableStateFlow(UiDiscover())

    @OptIn(ExperimentalCoroutinesApi::class)
    val discoverState: StateFlow<UiDiscover?> = _discoverState
        .flatMapLatest { state ->
            getDiscoverUseCase(page = state.page)
        }.scan(emptyList<Discover>()) { currentList, newList ->
            currentList + (newList)
        }.map { list ->
            _discoverState.updateAndGet {
                list.toUiDiscoverList()
            }
        }
        .stateInEagerly(_discoverState.value)


    fun onAction(action: DiscoverAction) {
        when (action) {
            is DiscoverAction.OnFilterClicked -> onUpdateFilter(action.uiFilter)
            is DiscoverAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
        }
    }


    private fun onUpdateFilter(uiFilter: UiFilter) {
        _discoverState.update { state ->
            state.copy(
                filter = _discoverState.value.filter.filter { currentFilter ->
                    currentFilter == uiFilter
                }.map {
                    it.copy(
                        isSeclected = !it.isSeclected
                    )
                }
            )
        }

        _discoverState.value.filter.forEach {
            logI<DiscoverViewModel>("updatedFilter: $it")
        }
    }


    private fun navigateToContentDetail(uiMediaCard: UiMediaCard) {
        navigation.navigateTo(
            destination = ContentDetailDestination(
                id = uiMediaCard.id,
                type = uiMediaCard.type
            )
        )
    }

}