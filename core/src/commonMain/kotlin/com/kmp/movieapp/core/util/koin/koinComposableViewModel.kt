package com.kmp.movieapp.core.util.koin

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.rememberViewModelStoreOwner
import org.koin.compose.currentKoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

@Composable
inline fun <reified VM : ViewModel> koinComposableViewModel(
    qualifier: Qualifier? = null,
    key: String? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null,
): VM {
    val viewModelStoreOwner = rememberViewModelStoreOwner()

    return koinViewModel(
        qualifier = qualifier,
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        scope = scope,
        parameters = parameters,
    )
}