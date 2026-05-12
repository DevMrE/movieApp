package com.kmp.movieapp.core.util.try_catch

import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.KClass

/**
 * Safely executes a suspend block and maps exceptions to values.
 *
 * @param T The type of the successful return value.
 * @param tryBlock The suspend block to execute.
 * @param handlers A map of exception classes to lambdas that produce a fallback value for that exception.
 * @return The result of [tryBlock] if successful, otherwise the mapped value from [handlers].
 */
suspend fun <T> multiCatch(
    tryBlock: suspend () -> T,
    handlers: Map<List<KClass<out Throwable>>, suspend (Throwable) -> T>
): T {
    return try {
        tryBlock()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        val matchingHandler = handlers.entries.firstOrNull { (keys, _) ->
            keys.any { it.isInstance(e) }
        }?.value

        if (matchingHandler != null) {
            matchingHandler(e)
        } else {
           throw e
        }
    }
}

suspend fun <T> multiCatch(
    tryBlock: suspend () -> T,
    vararg handlers: CatchHandler<T>
): T {
    return try {
        tryBlock()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        handlers.firstOrNull { it.type.isInstance(e) }
            ?.handler
            ?.invoke(e)
            ?: throw e
    }
}