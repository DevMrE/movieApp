package com.kmp.movieapp.core.util.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import com.kmp.movieapp.core.util.logger.logE
import kotlin.reflect.KClass

internal class NavigatorImpl<T : NavKey>(
    startDestination: T
) : Navigator<T> {

    override val backStack = mutableStateListOf(startDestination)

    override fun navigateTo(route: T, options: NavigationOptions) {
        val current = backStack.lastOrNull()

        // 1. Replace current screen
        if (options.replace && backStack.isNotEmpty()) {
            runCatching {
                backStack.removeLast()
            }.onFailure {
                logE<Navigator<T>>("navigateTo: replace didn't work!")
            }
            backStack += route
            return
        }

        // 2. popUpTo behavior
        options.popUpTo?.let { clazz ->
            val index = backStack.indexOfLast {
                clazz.isInstance(it)
            }

            if (index != -1) {
                val target = if (options.inclusive) index else index + 1

                while (backStack.size > target) {
                    runCatching {
                        backStack.removeLast()
                    }.onFailure {
                        logE<Navigator<T>>("navigateTo: popUpTo didn't work!")
                    }
                }
            }
        }

        // 3. singleTop
        if (options.launchSingleTop && current == route) return

        // 4. default push
        backStack += route
    }

    override fun switchTo(route: T) {
        if (backStack.isNotEmpty()) {
            runCatching {
                backStack.removeLast()
            }.onFailure {
                logE<Navigator<T>>("switchTo: remove didn't work!")
            }
        }

        backStack += route
    }

    override fun navigateBack() {
        if (backStack.size > 1) {
            runCatching {
                backStack.removeLast()
            }.onFailure {
                logE<Navigator<T>>("navigateBack: failed")
            }
        }
    }

    override fun popUpTo(
        clazz: KClass<out T>,
        inclusive: Boolean
    ) {

        val index = backStack.indexOfLast {
            clazz.isInstance(it)
        }

        if (index == -1) return

        val target = if (inclusive) index else index + 1

        while (backStack.size > target) {
            runCatching {
                backStack.removeLast()
            }.onFailure {
                logE<Navigator<T>>("remove last item in list failed!")
            }
        }
    }
}