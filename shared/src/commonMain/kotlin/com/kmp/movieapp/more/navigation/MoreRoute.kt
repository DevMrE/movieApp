package com.kmp.movieapp.more.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MoreRoute: NavKey {

    @Serializable
    data object InitialRoute: MoreRoute
}