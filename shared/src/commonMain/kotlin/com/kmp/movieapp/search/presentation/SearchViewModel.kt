package com.kmp.movieapp.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmre.compose.navigation3.helper.Navigator
import com.kmp.movieapp.genre.domain.repository.GenreRepository
import com.kmp.movieapp.model.UiMediaCard
import com.kmp.movieapp.search.domain.model.Filter
import com.kmp.movieapp.search.domain.usecase.SearchOrDiscoverUseCase
import com.kmp.movieapp.search.presentation.mapper.mapToFilter
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

    private val _searchState = MutableStateFlow(
        value = UiSearch(
            genreFilter = UiFilterType.Genre(
                genres = genreRepository.movieGenres.value.toUiGenreList()
            )
        )
    )

    val uiState: StateFlow<UiSearch> = _searchState

    init {
        viewModelScope.launch {
            // Load initial browse list
            updateSearchResult()
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
            _searchState.update {
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
            _searchState.update { state ->
                state.copy(
                    genreFilter = state.genreFilter?.copy(
                        genres = state.genreFilter.genres.map {
                            if (it == genre) it.copy(selected = !it.selected)
                            else it
                        }.sortedWith(comparator = compareByDescending { it.selected })
                    )
                )
            }

            updateSearchResult()
        }
    }

    private fun onSearch(query: String?) {
        viewModelScope.launch {
            _searchState.update { state ->
                state.copy(
                    search = query
                )
            }

            updateSearchResult()
        }
    }

    private fun updateSearchResult() {
        viewModelScope.launch {
            searchOrDiscoverUseCase(
                filter = Filter(
                    page = _searchState.value.page,
                    filterType = _searchState.value.mapToFilter()
                )
            ).collectLatest { (page, _, _, searchResult) ->
                _searchState.update {
                    it.copy(
                        page = page,
                        contentList = searchResult?.toUiMediaCardList() ?: emptyList(),
                    )
                }
            }
        }
    }
}