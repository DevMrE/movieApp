package com.kmp.navigation.compose_interface

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.kmp.navigation.navigation.NavDestination

class TypedGraph internal constructor(
    val install: NavGraphBuilder.() -> Unit
)

class TypedGraphBuilder internal constructor() {

    val installers = mutableListOf<NavGraphBuilder.() -> Unit>()

    inline fun <reified D : NavDestination> TypedGraphBuilder.screen(
        noinline content: @Composable (D) -> Unit
    ) {
        installers += {
            composable<D> { entry ->
                val dest = entry.toRoute<D>()
                content(dest)
            }
        }
    }

    inline fun <reified G : NavDestination, reified S : NavDestination> section(
        noinline block: TypedGraphBuilder.() -> Unit
    ) {
        val child = navGraph(block)
        installers += {
            navigation<G>(startDestination = S::class) {
                child.install(this)
            }
        }
    }

    internal fun build(): TypedGraph = TypedGraph {
        installers.forEach { it(this) }
    }
}

fun navGraph(block: TypedGraphBuilder.() -> Unit): TypedGraph {
    return TypedGraphBuilder().apply(block).build()
}

fun NavGraphBuilder.install(graph: TypedGraph) {
    graph.install(this)
}
