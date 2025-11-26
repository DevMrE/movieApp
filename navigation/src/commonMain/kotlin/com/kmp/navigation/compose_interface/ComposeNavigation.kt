package com.kmp.navigation.compose_interface

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.kmp.navigation.util.NavOptions
import com.kmp.navigation.util.NavDestination

/**
 * Compose-backed implementation of the `Navigation` API.
 *
 * Responsibilities:
 * - Holds a reference to a `NavHostController` (attached/detached via `MutableComposeNavigation`).
 * - Implements typed navigation calls (`navigateTo`, `switchTab`, `navigateUp`, `popBackTo`).
 * - Merkt sich pro "Root-Tab" (section<G, S>) automatisch die zuletzt besuchte Destination.
 */
internal class ComposeNavigation : MutableComposeNavigation {

    override fun attach(controller: NavHostController) {
        HandleNavigation.attach(controller)
    }

    override fun detach() {
        HandleNavigation.detach()
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> navigateTo(
        navDestination: D,
        options: NavOptions.() -> Unit
    ) {
        handleNavigateTo(navDestination, options)
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> switchTab(navDestination: D) {
        handleSwitchTo(navDestination)
    }

    context(viewModel: ViewModel)
    override fun navigateUp() {
        HandleNavigation.navigateUp()
    }

    context(viewModel: ViewModel)
    override fun <D : NavDestination> popBackTo(
        navDestination: D?,
        inclusive: Boolean
    ) {
        HandleNavigation.handlePopBackTo(navDestination, inclusive)
    }

    private fun <D : NavDestination> handleNavigateTo(
        navDestination: D,
        options: NavOptions.() -> Unit
    ) {
        HandleNavigation.handleNavigateTo(navDestination, options)
    }

    private fun <D : NavDestination> handleSwitchTo(navDestination: D) {
        HandleNavigation.handleSwitchTo(navDestination)
    }
}
