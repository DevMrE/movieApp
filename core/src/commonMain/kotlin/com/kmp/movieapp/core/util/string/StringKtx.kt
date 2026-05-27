package com.kmp.movieapp.core.util.string

fun String.clearInput() = this.trim().replace("\\s+".toRegex(), " ")

inline fun <T> Iterable<T>?.toPrefixedString(
    crossinline transform: (T) -> String?,
    separator: String,
    prefix: String = ""
): String? {
    val list = this
        ?.mapNotNull { transform(it)?.takeIf { value -> value.isNotBlank() } }
        ?.takeIf { it.isNotEmpty() }
        ?: return null

    return buildString {
        if (prefix.isNotEmpty()) append(prefix)
        append(list.joinToString(separator))
    }
}

val bulletPoint : String = "\u2022"