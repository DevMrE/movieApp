package com.kmp.movieapp.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.browse.mapper.toUiBrowseList
import com.kmp.movieapp.browse.mapper.toUiGenreList
import com.kmp.movieapp.browse.model.UiBrowse
import com.kmp.movieapp.browse.model.filter.UiFilterKey
import com.kmp.movieapp.browse.model.filter.UiFilterType
import com.kmp.movieapp.browse.model.filter.UiGenre
import com.kmp.movieapp.core.ui.content.model.UiMediaCard
import com.kmp.movieapp.core.util.logger.logI
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.route.BrowseNavigation
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.core.util.viewmodel.stateInLazily
import com.kmp.movieapp.discover.domain.usecase.GetDiscoverUseCase
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val navigator: Navigator<BrowseNavigation>,
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _genreState = MutableStateFlow(
        value = UiFilterType.Genre(
            genres = genreRepository.movieGenres.value.toUiGenreList()
        )
    )
    val genreState: StateFlow<UiFilterType.Genre> = _genreState
        .onEach { data ->
            _genreState.update {
                data.copy(
                    genres = data.genres.sortedWith(
                        comparator = compareByDescending { it.selected }
                    )
                )
            }
        }.stateInEagerly(initialData = _genreState.value)


    private val _browseState = MutableStateFlow<UiBrowse?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val browseState: StateFlow<UiBrowse?> = _browseState
        .flatMapLatest {
            getDiscoverUseCase(page = _browseState.value?.page ?: 1)
        }.map { data ->
            _browseState.value ?: _browseState.updateAndGet {
                data.toUiBrowseList()
            }
        }
        .stateInLazily(_browseState.value)

    fun onAction(action: BrowseAction) {
        when (action) {
            is BrowseAction.OnFilterClicked -> onUpdateFilter(action.filterKey)
            is BrowseAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
            is BrowseAction.OnGenreUpdated -> onUpdateGenre(action.genre)
        }
    }

    private fun onLoadNextPage() {
        viewModelScope.launch {
            _browseState.update {
                it?.copy(
                    page = it.page + 1
                )
            }
        }
    }

    private fun onUpdateFilter(uiFilterKey: UiFilterKey) {
        viewModelScope.launch {
            when (uiFilterKey) {
                is UiGenre -> {
                    _genreState.update { state ->
                        logI<BrowseViewModel>("state: $uiFilterKey")
                        logI<BrowseViewModel>("genreList: ${state.genres.size}")
                        state.genres.forEach {
                            logI<BrowseViewModel>("genre: $it")
                        }
                        state.copy(
                            genres = state.genres
                                .filter { uiFilterKey.id == it.id }
                                .map {
                                    logI<BrowseViewModel>("Selected: $uiFilterKey")
                                    uiFilterKey.copy(
                                        selected = !uiFilterKey.selected
                                    )
                                }
                        )
                    }
                }

                else -> Unit
            }
        }
    }


    private fun navigateToContentDetail(uiMediaCard: UiMediaCard) {
        logI(message = "Navigate with id: $uiMediaCard")
        navigator.navigateTo(
            route = BrowseNavigation.ContentDetailRoute(
                id = uiMediaCard.id,
                mediaCategory = uiMediaCard.type
            )
        )
    }

    private fun onUpdateGenre(genre: UiGenre) {
        _genreState.update { state ->
            state.copy(
                genres = state.genres.map {
                    val update =
                        if (it == genre) genre.copy(selected = !genre.selected) else it
                    update
                }
            )
        }
    }

}