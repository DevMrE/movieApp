package com.kmp.movieapp.components.topbar.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.devmre.compose.navigation3.helper.Navigator
import org.jetbrains.compose.resources.StringResource
import org.koin.core.Koin

data class TopBarConfig(
    val title: StringResource? = null,
    val onBackClick: (() -> Unit)? = null,
)

fun interface TopBarResolver {
    fun resolve(key: Any, koin: Koin): TopBarConfig
}

object TopBarMetadata {
    const val KEY = "top_bar_config"

    // Static config
    fun config(config: TopBarConfig): Map<String, Any> =
        mapOf(KEY to TopBarResolver { _, _ -> config })

    // Dynamic config: route arguments and Koin (e.g. the navigator) are available
    inline fun <reified K : Any> config(noinline block: Koin.(K) -> TopBarConfig): Map<String, Any> =
        mapOf(KEY to TopBarResolver { key, koin -> koin.block(key as K) })
}

fun NavEntry<*>.topBarConfig(key: Any?, koin: Koin): TopBarConfig =
    (metadata[TopBarMetadata.KEY] as? TopBarResolver)
        ?.let { resolver -> key?.let { resolver.resolve(it, koin) } }
        ?: TopBarConfig()

@Composable
fun <T : NavKey> rememberTopBarConfig(
    navigator: Navigator<T>,
    entryProvider: (T) -> NavEntry<T>,
    koin: Koin,
): TopBarConfig {
    val key = navigator.backStack.lastOrNull()

    return remember(key) {
        key?.let { entryProvider(it).topBarConfig(it, koin) }
    } ?: TopBarConfig()
}