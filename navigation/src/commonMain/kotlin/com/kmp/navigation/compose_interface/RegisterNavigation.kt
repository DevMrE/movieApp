package com.kmp.navigation.compose_interface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kmp.navigation.navigation.LocalNavigator
import com.kmp.navigation.navigation.NavDestination

/**
 * Installs a `NavHost` and provides a `Navigation` instance to the composition.
 *
 * - `startNavDestination`: the initial destination instance.
 * - `content`: build the typed graph using e.g. `screen<Dest> { ... }` or `install(graph)`.
 *
 * Internally:
 * - Creates a `NavHostController` and wires it to `MutableComposeNavigation`.
 * - Exposes the navigator via `LocalNavigator` for child composables.
 */
@Composable
fun RegisterNavigation(
    startNavDestination: NavDestination,
    modifier: Modifier = Modifier,
    content: NavGraphBuilder.() -> TypedGraph
) {
    val navController = rememberNavController()
    val mutableComposeNavigation = rememberMutableComposeNavigation(navController)

    CompositionLocalProvider(LocalNavigator provides mutableComposeNavigation) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startNavDestination
        ) {
            install(content())
        }
    }
}
