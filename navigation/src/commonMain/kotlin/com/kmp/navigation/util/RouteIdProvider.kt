package com.kmp.navigation.util

import kotlin.reflect.KClass

fun interface RouteIdProvider {
    fun idFor(clazz: KClass<out NavDestination>): Int
}