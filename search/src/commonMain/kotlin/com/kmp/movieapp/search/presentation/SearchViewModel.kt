package com.kmp.movieapp.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.movieapp.core.util.viewmodel.stateInEagerly
import com.kmp.movieapp.search.domain.usecase.SearchUseCase
import com.kmp.movieapp.search.presentation.action.SearchAction
import com.kmp.movieapp.search.presentation.mapper.mapToUiData
import com.kmp.movieapp.search.presentation.model.UiSearchState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchQueryState = MutableStateFlow(UiSearchState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchQueryState: StateFlow<UiSearchState> = _searchQueryState
        .onEach {
            viewModelScope.launch {
                searchUseCase(_searchQueryState.value.search).collectLatest { results ->
                    _searchQueryState.update {
                        it.copy(
                            searchResults = results.mapToUiData()
                        )
                    }
                }
            }
        }.stateInEagerly(_searchQueryState.value)

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchChanged -> updateSearch(action.query)
            is SearchAction.OnSearchActiveChanged -> updateActiveSearch()
        }
    }

    private fun updateSearch(query: String) {
        _searchQueryState.update {
            it.copy(
                search = query
            )
        }
    }

    private fun updateActiveSearch() {
        _searchQueryState.update {
            it.copy(
                isSearchActive = !it.isSearchActive
            )
        }
    }
}