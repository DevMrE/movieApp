package com.kmp.movieapp.core.util.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import com.devmre.compose.navigation3.helper.NavigationOptions
import com.devmre.compose.navigation3.helper.Navigator
import kotlin.reflect.KClass

data object PreviewRoute : NavKey

object PreviewNavigation : Navigator<PreviewRoute> {
    override val backStack: SnapshotStateList<PreviewRoute>
        get() = SnapshotStateList()

    override fun getCurrentBackStack(): PreviewRoute {
        return PreviewRoute
    }

    override fun navigateTo(
        route: PreviewRoute,
        options: NavigationOptions
    ) {
    }

    override fun switchTo(route: PreviewRoute) {
    }

    override fun navigateBack() {
    }

    override fun popUpTo(
        clazz: KClass<out PreviewRoute>,
        inclusive: Boolean
    ) {
    }
}
