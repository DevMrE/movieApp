package com.kmp.movieapp.core.util.navigation.util

import androidx.compose.runtime.Composable
import com.kmp.movieapp.core.util.navigation.Navigator
import com.kmp.movieapp.core.util.navigation.Route
import org.koin.compose.koinInject

@Composable
inline fun <reified T : Route> koinNavigation(): Navigator<T> {
    return koinInject<Navigator<T>>(qualifier = navigatorQualifier<T>())
}