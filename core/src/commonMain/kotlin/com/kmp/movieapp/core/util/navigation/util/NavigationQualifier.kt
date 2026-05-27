package com.kmp.movieapp.core.util.navigation.util

import androidx.navigation3.runtime.NavKey
import org.koin.core.qualifier.named

inline fun <reified T : NavKey> navigatorQualifier() =
    named("navigator_${T::class.qualifiedName}")