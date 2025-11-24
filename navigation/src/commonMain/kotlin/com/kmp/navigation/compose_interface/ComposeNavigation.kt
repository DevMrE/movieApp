package com.kmp.navigation.compose_interface

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.serialization.generateHashCode
import com.kmp.navigation.navigation.NavDestination
import com.eu.de.mre.movieapp.util.navigation.NavOptions
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

/**
 * Compose-backed implementation of the `Navigation` API.
 *
 * Responsibilities:
 * - Holds a reference to a `NavHostController` (attached/detached via `MutableComposeNavigation`).
 * - Implements typed navigation calls (`navigateTo`, `switchTab`, `navigateUp`, `popBackTo`).
 * - Applies `NavOptions` flags when navigating.
 *
 * Obtained via DI in `di/navigationModule.kt` and wired to a `NavHost` by `RegisterNavigation`.
 */
class ComposeNavigation : MutableComposeNavigation {

    private var navController: NavHostController? = null

    override fun attach(controller: NavHostController) {
        navController = controller
    }

    override fun detach() {
        navController = null
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> navigateTo(
        navDestination: D,
        options: NavOptions.() -> Unit
    ) {
        val opts = NavOptions().apply(options).copy(restoreState = true)
        navController?.navigate(navDestination) {
            launchSingleTop = opts.singleTop
            if (opts.restoreState) restoreState = true

            when (val backstack = opts.backstack) {
                is NavOptions.Backstack.PopTo -> {
                    popUpTo(backstack.navDestination) {
                        inclusive = backstack.inclusive
                        saveState = backstack.saveState
                    }
                }

                is NavOptions.Backstack.Clear -> {
                    navController?.graph?.id?.let {
                        popUpTo(it) { inclusive = false }
                    }
                }

                is NavOptions.Backstack.None -> Unit
            }
        }
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> switchTab(navDestination: D) {
        val opts = NavOptions(singleTop = true, restoreState = true)
        navController?.navigate(navDestination) {
            launchSingleTop = opts.singleTop
            if (opts.restoreState) restoreState = true

            when (val backstack = opts.backstack) {
                is NavOptions.Backstack.PopTo -> {
                    popUpTo(backstack.navDestination) {
                        inclusive = backstack.inclusive
                        saveState = backstack.saveState
                    }
                }

                else -> {
                    navController?.graph?.id?.let {
                        popUpTo(it) { inclusive = false }
                    }
                }
            }
        }
    }

    context(viewModel: ViewModel)
    override fun navigateUp() {
        navController?.navigateUp()
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> popBackTo(
        navDestination: D?,
        inclusive: Boolean
    ) {
        if (navDestination == null) navController?.popBackStack()
        else {
            val ok = navController?.popBackStack(navDestination, inclusive = inclusive)
            ok?.let { if (!it) navController?.popBackStack() }
        }
    }
}

@OptIn(InternalSerializationApi::class)
private fun KClass<*>.routeId(): Int = this.serializer().generateHashCode()
