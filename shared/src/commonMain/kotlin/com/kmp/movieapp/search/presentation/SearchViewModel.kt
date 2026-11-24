package com.kmp.movieapp.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmre.compose.navigation3.helper.Navigator
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.domain.model.Browse
import com.kmp.movieapp.search.domain.model.discover.Discover
import com.kmp.movieapp.search.domain.model.discover.Filter
import com.kmp.movieapp.search.domain.usecase.SearchOrDiscoverUseCase
import com.kmp.movieapp.search.presentation.mapper.mapToFilter
import com.kmp.movieapp.search.presentation.mapper.mapToUiMediaCardList
import com.kmp.movieapp.search.presentation.mapper.toUiGenreList
import com.kmp.movieapp.search.presentation.mapper.toUiMediaCardList
import com.kmp.movieapp.search.presentation.model.UiSearch
import com.kmp.movieapp.search.presentation.model.filter.UiFilterType
import com.kmp.movieapp.search.presentation.model.filter.UiGenre
import com.kmp.movieapp.search.presentation.navigation.SearchRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchViewModel(
    genreRepository: GenreRepository,
    private val navigator: Navigator<SearchRoute>,
    private val searchOrDiscoverUseCase: SearchOrDiscoverUseCase
) : ViewModel() {

    private val _browseState = MutableStateFlow(
        value = UiSearch(
            genreFilter = UiFilterType.Genre(
                genres = genreRepository.movieGenres.value.toUiGenreList()
            )
        )
    )

    val browseState: StateFlow<UiSearch> = _browseState

    init {
        viewModelScope.launch {
            // Load initial browse list
            updateDiscover(
                page = _browseState.value.page,
                filter = _browseState.value.mapToFilter()
            )
        }
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchUpdated -> onSearch(action.query)
            is SearchAction.OnContentClicked -> navigateToContentDetail(action.uiMediaCard)
            is SearchAction.OnGenreUpdated -> onUpdateGenre(action.genre)
            is SearchAction.OnLoadNextPage -> Unit
        }
    }

    private fun onLoadNextPage() {
        viewModelScope.launch {
            _browseState.update {
                it.copy(
                    page = it.page + 1
                )
            }
        }
    }

    private fun navigateToContentDetail(uiMediaCard: UiMediaCard) {
        navigator.navigateTo(
            route = SearchRoute.ContentDetailRoute(
                id = uiMediaCard.id,
                mediaCategory = uiMediaCard.type
            )
        )
    }

    private fun onUpdateGenre(genre: UiGenre) {
        viewModelScope.launch {
            _browseState.update { state ->
                state.copy(
                    search = null,
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map {
                            if (it == genre) it.copy(selected = !it.selected)
                            else it
                        }.sortedWith(comparator = compareByDescending { it.selected })
                    )
                )
            }

            updateDiscover(
                page = _browseState.value.page,
                filter = _browseState.value.mapToFilter()
            )
        }
    }

    private fun onSearch(query: String?) {
        viewModelScope.launch {
            _browseState.update { state ->
                state.copy(
                    search = query,
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map { it.copy(selected = false) }
                    )
                )
            }

            searchOrDiscoverUseCase(
                browse = Browse(page = 1, query = query)
            ).collectLatest { (page, _, search, _) ->
                _browseState.update {
                    it.copy(
                        page = page,
                        contentList = search?.mapToUiMediaCardList() ?: it.contentList,
                    )
                }
            }
        }
    }

    private fun updateDiscover(page: Int = 1, filter: Filter? = null) {
        viewModelScope.launch {
            searchOrDiscoverUseCase(
                browse = Browse(page = page, discover = Discover(filter = filter))
            ).collectLatest { (page, _, _, discover) ->
                _browseState.update {
                    it.copy(
                        page = page,
                        contentList = discover?.discoverContent?.toUiMediaCardList() ?: emptyList(),
                    )
                }
            }
        }
    }
}