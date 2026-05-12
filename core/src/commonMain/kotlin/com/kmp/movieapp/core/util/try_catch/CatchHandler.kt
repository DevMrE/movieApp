package com.kmp.movieapp.core.util.try_catch

import kotlin.reflect.KClass

data class CatchHandler<T>(
    val type: KClass<out Throwable>,
    val handler: suspend (Throwable) -> T
)

inline fun <reified E : Throwable, T> handler(
    noinline block: suspend (E) -> T
): CatchHandler<T> {
    return CatchHandler(
        type = E::class,
        handler = { e -> block(e as E) }
    )
}