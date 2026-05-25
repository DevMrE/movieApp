package com.kmp.movieapp.core.util.navigation.util

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import com.kmp.movieapp.core.util.navigation.Navigator
import org.koin.compose.koinInject

@Composable
inline fun <reified T : NavKey> koinNavigation(): Navigator<T> {
    return koinInject<Navigator<T>>(qualifier = navigatorQualifier<T>())
}