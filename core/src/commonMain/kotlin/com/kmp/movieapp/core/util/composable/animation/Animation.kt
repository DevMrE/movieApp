package com.kmp.movieapp.core.util.composable.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

data class MotionValue(
    val hidden: Float,
    val visible: Float,
    val spec: FiniteAnimationSpec<Float>,
)

data class ModifierAnimation(
    val alpha: MotionValue? = null,
    val scaleX: MotionValue? = null,
    val scaleY: MotionValue? = null,
    val translationX: MotionValue? = null,
    val translationY: MotionValue? = null,
) {
    operator fun plus(other: ModifierAnimation) = ModifierAnimation(
        alpha = other.alpha ?: alpha,
        scaleX = other.scaleX ?: scaleX,
        scaleY = other.scaleY ?: scaleY,
        translationX = other.translationX ?: translationX,
        translationY = other.translationY ?: translationY,
    )
}

private val DefaultSpec = tween<Float>(
    durationMillis = 300,
    easing = FastOutSlowInEasing,
)

fun fadeIn(
    initialAlpha: Float = 0f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    alpha = MotionValue(initialAlpha, 1f, animationSpec),
)

fun fadeOut(
    targetAlpha: Float = 0f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    alpha = MotionValue(targetAlpha, 1f, animationSpec),
)

fun scaleIn(
    initialScale: Float = 0.8f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    scaleX = MotionValue(initialScale, 1f, animationSpec),
    scaleY = MotionValue(initialScale, 1f, animationSpec),
)

fun scaleOut(
    targetScale: Float = 0.8f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    scaleX = MotionValue(targetScale, 1f, animationSpec),
    scaleY = MotionValue(targetScale, 1f, animationSpec),
)

fun slideInVertically(
    initialOffset: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    translationY = MotionValue(initialOffset, 0f, animationSpec),
)

fun slideOutVertically(
    targetOffset: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    translationY = MotionValue(targetOffset, 0f, animationSpec),
)

fun slideInHorizontally(
    initialOffset: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    translationX = MotionValue(initialOffset, 0f, animationSpec),
)

fun slideOutHorizontally(
    targetOffset: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = DefaultSpec,
) = ModifierAnimation(
    translationX = MotionValue(targetOffset, 0f, animationSpec),
)


fun Modifier.animate(
    visible: Boolean,
    enter: ModifierAnimation,
    exit: ModifierAnimation,
): Modifier = composed {
    val transition = updateTransition(
        targetState = visible,
        label = "ModifierAnimation",
    )

    @Composable
    fun animateValue(
        enterValue: MotionValue?,
        exitValue: MotionValue?,
        defaultValue: Float,
        label: String,
    ): Float {
        val enterSpec = enterValue?.spec ?: DefaultSpec
        val exitSpec = exitValue?.spec ?: DefaultSpec

        return transition.animateFloat(
            transitionSpec = {
                if (targetState) enterSpec else exitSpec
            },
            label = label,
        ) { state ->
            when {
                state -> enterValue?.visible ?: defaultValue
                else -> exitValue?.hidden ?: defaultValue
            }
        }.value
    }

    val alpha = animateValue(
        enter.alpha,
        exit.alpha,
        1f,
        "Alpha",
    )

    val scaleX = animateValue(
        enter.scaleX,
        exit.scaleX,
        1f,
        "ScaleX",
    )

    val scaleY = animateValue(
        enter.scaleY,
        exit.scaleY,
        1f,
        "ScaleY",
    )

    val translationX = animateValue(
        enter.translationX,
        exit.translationX,
        0f,
        "TranslationX",
    )

    val translationY = animateValue(
        enter.translationY,
        exit.translationY,
        0f,
        "TranslationY",
    )

    graphicsLayer {
        this.alpha = alpha
        this.scaleX = scaleX
        this.scaleY = scaleY

        this.translationX = translationX * size.width
        this.translationY = translationY * size.height
    }
}
