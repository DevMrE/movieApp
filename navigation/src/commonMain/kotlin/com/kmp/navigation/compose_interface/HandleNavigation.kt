package com.kmp.navigation.compose_interface

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.serialization.generateHashCode
import co.touchlab.kermit.Logger
import com.kmp.navigation.util.NavDestination
import com.kmp.navigation.util.NavOptions
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

internal object HandleNavigation {
    var navController: NavHostController? = null
        private set
    private val lastDestinationByRootId = mutableMapOf<Int, NavDestination>()

    fun attach(controller: NavHostController) {
        navController = controller
    }

    fun detach() {
        navController = null
    }


    fun <D : NavDestination> handleNavigateTo(
        navDestination: D,
        options: NavOptions.() -> Unit
    ) {
        val controller = navController ?: return

        val targetId = navDestination::class.routeId()
        val currentId = controller.currentDestination?.id

        if (currentId == targetId) return

        try {
            val opts = NavOptions().apply(options)

            controller.navigate(navDestination) {
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
                        controller.graph.id.let {
                            popUpTo(it) { inclusive = false }
                        }
                    }

                    is NavOptions.Backstack.None -> Unit
                }
            }

            rootIdForDestinationId(targetId)?.let { rootId ->
                lastDestinationByRootId[rootId] = navDestination
            }

        } catch (e: IllegalArgumentException) {
            e.message?.let {
                Logger.e(tag = "Navigation", messageString = it, throwable = e)
            }
        }
    }

    fun <D : NavDestination> handleSwitchTo(navDestination: D) {
        val controller = navController ?: return

        val rootTypeId = navDestination::class.routeId()
        val rootId = rootIdForDestinationId(rootTypeId)

        val effectiveDestination: NavDestination = if (rootId != null) {
            lastDestinationByRootId[rootId] ?: navDestination
        } else {
            navDestination
        }

        val targetId = effectiveDestination::class.routeId()
        val currentId = controller.currentDestination?.id

        if (currentId == targetId) return

        try {
            controller.navigate(effectiveDestination) {
                // Standard-BottomNav-Pattern aus der Navigation-Doku:
                launchSingleTop = true
                restoreState = true

                popUpTo(controller.graph.findStartDestination().id) {
                    saveState = true
                }
            }

            // Nach dem Tab-Wechsel: letzte Destination unter diesem Root aktualisieren
            rootIdForDestinationId(targetId)?.let { resolvedRootId ->
                lastDestinationByRootId[resolvedRootId] = effectiveDestination
            }

        } catch (e: Exception) {
            e.message?.let {
                Logger.e(tag = "Navigation", messageString = it, throwable = e)
            }
        }
    }

    private fun rootIdForDestinationId(targetId: Int): Int? {
        val controller = navController ?: return null
        val rootGraph = controller.graph
        val node = rootGraph.findNode(targetId) ?: return null

        val parent = node.parent ?: return node.id

        if (parent.id == rootGraph.id) {
            return node.id
        }

        var currentParent = parent
        while (currentParent.parent != null && currentParent.parent?.id != rootGraph.id) {
            currentParent = currentParent.parent!!
        }

        return currentParent.id
    }

    fun <D : NavDestination> handlePopBackTo(
        navDestination: D?,
        inclusive: Boolean
    ) {
        val controller = navController ?: return

        if (navDestination == null) {
            controller.popBackStack()
        } else {
            val ok = controller.popBackStack(navDestination, inclusive = inclusive)
            if (!ok) controller.popBackStack()
        }
    }

    fun navigateUp() {
        navController?.navigateUp()
    }

    @OptIn(InternalSerializationApi::class)
    fun KClass<*>.routeId(): Int = this.serializer().generateHashCode()
}