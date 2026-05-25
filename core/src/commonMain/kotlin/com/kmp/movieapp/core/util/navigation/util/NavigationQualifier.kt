package com.kmp.movieapp.core.util.navigation.util

import com.kmp.movieapp.core.util.navigation.Route
import org.koin.core.qualifier.named

inline fun <reified T : Route> navigatorQualifier() =
    named("navigator_${T::class.qualifiedName}")