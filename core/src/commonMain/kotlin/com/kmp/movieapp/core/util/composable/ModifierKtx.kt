package com.kmp.movieapp.core.util.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush

/**
 * Adds a gradient overlay over the actual content.
 */
fun Modifier.gradientOverlay(brush: Brush) = this.then(
    other = Modifier.drawWithCache {
        onDrawWithContent {
            // Show first the actual content
            drawContent()
            // add the brush over the content
            drawRect(brush = brush)
        }
    }
)

/**
 * Conditionally applies a [Modifier] transformation.
 *
 * This extension allows adding modifiers to the chain only when [condition] is true,
 * keeping the modifier chain clean and avoiding external branching (if/else).
 *
 * Internally, a new [Modifier] instance is passed to [block] and appended via [then],
 * ensuring that the existing modifier chain remains immutable and predictable.
 *
 * @param condition Determines whether the [block] should be applied.
 * @param block A lambda that returns the modifier(s) to apply when [condition] is true.
 *
 * @return The original [Modifier] if [condition] is false, otherwise the result of
 * appending the produced modifier via [then].
 *
 * @example
 * ```
 * Modifier
 *     .fillMaxWidth()
 *     .applyIf(isSelected) {
 *         background(Color.Red)
 *     }
 *     .padding(16.dp)
 * ```
 */
inline fun Modifier.applyIf(
    condition: Boolean,
    block: Modifier.() -> Modifier
): Modifier = if (condition) then(block(Modifier)) else this

/**
 * Conditionally applies one of two [Modifier] transformations.
 *
 * This extension enables branching directly within the modifier chain,
 * applying [ifTrue] when [condition] is true, or [ifFalse] otherwise.
 *
 * Both branches operate on a fresh [Modifier] instance and are appended via [then],
 * preserving immutability and preventing unintended modifier chaining side effects.
 *
 * @param condition Determines which block should be applied.
 * @param ifTrue A lambda producing the modifier(s) when [condition] is true.
 * @param ifFalse A lambda producing the modifier(s) when [condition] is false.
 *
 * @return The original [Modifier] with the selected transformation appended.
 *
 * @example
 * ```
 * Modifier.applyIfElse(
 *     condition = isError,
 *     ifTrue = {
 *         border(1.dp, Color.Red)
 *     },
 *     ifFalse = {
 *         border(1.dp, Color.Gray)
 *     }
 * )
 * ```
 */
inline fun Modifier.applyIfElse(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}