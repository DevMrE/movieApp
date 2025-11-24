package com.kmp.navigation.di

import com.kmp.navigation.compose_interface.ComposeNavigation
import com.kmp.navigation.compose_interface.MutableComposeNavigation
import com.kmp.navigation.navigation.Navigation
import org.koin.dsl.module

val navigationModule = module {

    single<MutableComposeNavigation> { ComposeNavigation() }
    single<Navigation> { get<MutableComposeNavigation>() }
}