package com.kmp.movieapp.core.util.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

context(viewModel: ViewModel)
fun <T> Flow<T>.stateInLazily(initialData: T): StateFlow<T> {
    return stateIn(
        viewModel.viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = initialData
    )
}

context(viewModel: ViewModel)
fun <T> Flow<T>.stateInEagerly(initialData: T): StateFlow<T> {
    return stateIn(
        viewModel.viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = initialData
    )
}

context(viewModel: ViewModel)
fun <T> Flow<T>.stateInSubscribed(initialData: T, subscriptionDuration: Long = 2000): StateFlow<T> {
    return stateIn(
        viewModel.viewModelScope,
        started = SharingStarted.WhileSubscribed(subscriptionDuration),
        initialValue = initialData
    )
}