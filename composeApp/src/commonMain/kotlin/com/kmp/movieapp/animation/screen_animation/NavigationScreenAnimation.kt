package com.kmp.movieapp.animation.screen_animation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation3.ui.NavDisplay

object NavigationScreenAnimation {
    fun bottomSheetTransitions(): Map<String, Any> {
        return NavDisplay.predictivePopTransitionSpec { swipeEdge ->
            ContentTransform(
                targetContentEnter = fadeIn(),
                initialContentExit = slideOutVertically(
                    animationSpec = tween(600),
                    targetOffsetY = { it }
                )
            )
        } + NavDisplay.popTransitionSpec {
            ContentTransform(
                targetContentEnter = fadeIn(),
                initialContentExit = slideOutVertically(
                    animationSpec = tween(600),
                    targetOffsetY = { it }
                )
            )
        } + NavDisplay.transitionSpec {
            ContentTransform(
                targetContentEnter = slideInVertically(
                    animationSpec = tween(600),
                    initialOffsetY = { it }
                ),
                initialContentExit = fadeOut()
            )
        }
    }

    fun slideSheetTransition(): Map<String, Any> {
        return NavDisplay.predictivePopTransitionSpec {
            ContentTransform(
                targetContentEnter = fadeIn(),
                initialContentExit = slideOutHorizontally(
                    animationSpec = tween(600),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            )
        } + NavDisplay.popTransitionSpec {
            ContentTransform(
                targetContentEnter = fadeIn(),
                initialContentExit = slideOutHorizontally(
                    animationSpec = tween(600),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            )
        }
    }
}