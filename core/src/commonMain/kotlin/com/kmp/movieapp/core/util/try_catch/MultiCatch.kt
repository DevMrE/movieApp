package com.kmp.movieapp.core.util.try_catch

import kotlin.coroutines.cancellation.CancellationException

/**
 * Safely executes a suspend block and maps thrown exceptions to fallback values.
 *
 * This function provides a declarative mechanism for handling exceptions in coroutine-based
 * execution by routing thrown exceptions to type-based handlers.
 *
 * It is generic and not limited to networking use cases. It can be applied to parsing,
 * validation, IO, etc.
 *
 * ## Behavior
 * - Executes [tryBlock] and returns its result if successful.
 * - Catches exceptions and routes them to the first matching handler based on runtime type.
 * - Type matching is performed using `isInstance` (supports inheritance hierarchy).
 * - If multiple handlers could match a given exception, **only the first matching handler is executed**.
 * - If no handler matches, the exception is rethrown.
 * - `CancellationException` is always rethrown and never intercepted.
 *
 * ## ⚠️ Important: Handler Order is Critical
 * Handlers are evaluated in declaration order.
 *
 * This means:
 * - More specific exception types **must be registered before** more general types.
 * - A generic handler such as `Exception::class` will catch all exceptions unless placed last.
 * - Incorrect ordering can lead to unintended handler execution and lost specificity.
 *
 * Example:
 * ```
 * Exception::class { ... }          // will catch everything if placed first
 * JsonParseException::class { ... } // will never be reached if above catches first
 * ```
 *
 * Correct ordering:
 * ```
 * JsonParseException::class { ... }
 * Exception::class { ... }          // fallback
 * ```
 *
 * ## Typical Use Cases
 * - JSON parsing failures (e.g. JsonParseException)
 * - Illegal state or validation errors
 * - HTTP/network errors (e.g. ResponseException)
 * - IO and file system errors
 *
 * @param T The return type of the successful execution.
 * @param tryBlock The suspend block to execute safely.
 * @param handlers A list of type-based exception handlers mapped to fallback values.
 *
 * @return The result of [tryBlock] if successful, otherwise the value returned by the first matching handler.
 *
 * @throws Throwable if no matching handler is found (excluding CancellationException).
 */
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