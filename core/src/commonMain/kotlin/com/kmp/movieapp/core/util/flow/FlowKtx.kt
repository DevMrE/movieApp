package com.kmp.movieapp.core.util.flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

inline fun <T> MutableStateFlow<T>.updateState(
    update: T.() -> T
) {
    this.update { it.update() }
}


class StateScope<T>(var state: T)

inline fun <T> MutableStateFlow<T>.updateStateDsl(
    block: StateScope<T>.() -> Unit
) {
    this.update { old ->
        val scope = StateScope(old)
        scope.block()
        scope.state
    }
}
