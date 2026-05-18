package com.kmp.movieapp.core.util.logger

import co.touchlab.kermit.Logger

private const val MAX_LOG_LENGTH = 4000

inline fun <reified T : Any> logE(message: String) =
    try {
        T::class.simpleName?.let {
            val logger = Logger.withTag(it)
            logger.e(messageString = message)
        }
    } catch (e: Exception) {

    }

inline fun <reified T : Any> logE(message: String, throwable: Throwable) =
    try {
        T::class.simpleName?.let {
            val logger = Logger.withTag(it)
            logger.e(messageString = message, throwable = throwable)
        }
    } catch (e: Exception) {
    }

inline fun <reified T : Any> logI(message: String) =
    try {
        T::class.simpleName?.let {
            val logger = Logger.withTag(it)
            logger.i(messageString = message)
        }
    } catch (e: Exception) {

    }

fun logI(message: String) =
    try {
        val logger = Logger.withTag("MovieApp")

        var start = 0
        val length = message.length

        while (start < length) {
            val end = (start + MAX_LOG_LENGTH).coerceAtMost(length)
            logger.i(messageString = message.substring(start, end))
            start = end
        }
    } catch (e: Exception) {

    }

fun logE(message: String) =
    try {
        val logger = Logger.withTag("MovieApp")

        var start = 0
        val length = message.length

        while (start < length) {
            val end = (start + MAX_LOG_LENGTH).coerceAtMost(length)
            logger.i(messageString = message.substring(start, end))
            start = end
        }
    } catch (e: Exception) {

    }


