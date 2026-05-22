package com.kmp.movieapp.navigation

fun registerAppNavigation() {
//    registerNavigation(startDestination = HomeDestination) {
//        content<HomeDestination> { HomeContent() }
//        content<SettingsDestination> { SettingsContent() }
//        content<HomeMediaListDestination>(
//            enterTransition = {
//                scaleIn()
//            },
//            exitTransition = {
//                scaleOut()
//            }
//        ) { data ->
//            logI("HomeMediaList?: $data")
//            MediaListScreen(data.mediaCategory)
//        }
//
//        content<DiscoverMediaDestination> {
//            DiscoverScreen()
//        }
//
//        screen<MediaDetailDestination> { dest ->
//            ContentDetailScreen(
//                id = dest.id,
//                mediaCategory = dest.mediaCategory
//            )
//        }
//
//        screen<ContentDetailDestination> { dest ->
//            ContentDetailScreen(
//                id = dest.id,
//                mediaCategory = dest.type
//            )
//        }
//
//        content<SearchScreenDestination> {
//            SearchContent()
//        }
//
//        tabs<BottomBarTabs>(
//            startDestination = HomeDestination,
//            HomeDestination, SettingsDestination, DiscoverMediaDestination
//        )
//    }
}