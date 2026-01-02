package com.kmp.movieapp.core.util

import com.kmp.movieapp.core.util.Result.Failure
import com.kmp.movieapp.core.util.Result.Success

/**
 * This class represents the [Result] of [Success] <[SuccessData]> and possible [Failure] with optionally an <[FallbackData]>.
 *
 * @param <[SuccessData]> success type.
 * @param <[FallbackData]> failure type. Use [Unit] for none failure type.
 *
 * @sample ResultSample
 */
sealed class Result<out SuccessData, out FallbackData> {

    /**
     * Getter for the failure.
     *
     * @return the error if this is a [Failure] or null if this is a [Success]
     */
    val failure: Failure<FallbackData>? get() = this as? Failure

    /**
     * Represents a successful response.
     *
     * @param value is the result of the successful api invocation.
     */
    class Success<out SuccessData>(val value: SuccessData) : Result<SuccessData, Nothing>()

    /**
     * A result of type [Failure] which represents some error.
     *
     * @param message optional description of the [Failure].
     * @param throwable optional exception which caused the [Failure].
     * @param cause optional cause of the [Failure].
     * @param value optional value which can provide additional data.
     */
    open class Failure<out FallbackData>(
        val message: String? = "",
        val throwable: Throwable? = null,
        val cause: Failure<Any>? = null,
        val value: FallbackData? = null
    ) :
        Result<Nothing, FallbackData>() {

        override fun toString() =
            "Failure(\n" +
                    "value=$value\n" +
                    "message=$message\n" +
                    "throwable=$throwable\n" +
                    "cause=$cause\n" +
                    ")"
    }
}

/**
 * Executes the given block and returns its [Result] if `this` is a [Success]. Otherwise
 * the block is ignored and `this` (which is a [Failure]) is returned.
 *
 * @param block to execute
 *
 * @return the [Result] of block or this.
 */
inline fun <SuccessData, FallbackData, ResultOfBlock> Result<SuccessData, FallbackData>.runOnSuccess(block: (SuccessData) -> Result<ResultOfBlock, FallbackData>): Result<ResultOfBlock, FallbackData> =
    this as? Failure ?: block((this as Success<SuccessData>).value)


/**
 * Executes the given block if this is a [Failure].
 * Always returns `this`.
 *
 * @param block to execute
 *
 * @return `this`
 */
inline fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.alsoOnFailure(block: (Failure<FallbackData>) -> Unit): Result<SuccessData, FallbackData> {
    if (this is Failure) {
        block(this)
    }
    return this
}

/**
 * Executes the given block if this is a [Success].
 * Always returns `this`.
 *
 * @param block to execute
 *
 * @return `this`
 */
inline fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.alsoOnSuccess(block: (SuccessData) -> Unit): Result<SuccessData, FallbackData> {
    if (this is Success) {
        block((this).value)
    }
    return this
}

private object ResultSample {

    // Return result without fallback data Result<SampleData, Nothing>
    fun getDataFromApiCall(): Result<SampleDtoFromRemoteCall, Nothing> {
        return Success(SampleDtoFromRemoteCall(user = "user"))
    }

    // Return result with fallback data Result<SampleData, ExceptionLoggerClass>
    fun getDataWithFallbackData(): Result<SampleDtoFromRemoteCall, ExceptionLoggerClass> {
        return try {
            Success(SampleDtoFromRemoteCall(user = "user"))
        } catch (e: Exception) {
            Failure(value = ExceptionLoggerClass(log = e.message))
        }
    }

    // Emitting data to Success, Failure and Loading
    fun sample(): Result<SampleDtoFromRemoteCall, Unit> {
        return try {
            // emit if data was successfull loading
            Success(SampleDtoFromRemoteCall(user = "user"))
        } catch (e: Exception) {
            // Catching data if there was a failure.
            Failure(e.message)
        }
    }

    // --------------------------------------------------
    // Calling the emitted data
    fun getDataFromCall() {
        getDataFromApiCall()
            .alsoOnSuccess { emittedData ->
                // Use the emitted data
                emittedData.user
            }
            .alsoOnFailure { data ->
                // Handle the exception
                handleException(data)
                // Optionally you can set a fallback like a
                // exception handler and call it with
                data.value
            }
    }
}


// Sample Data to show it in the documentation.
private data class SampleDtoFromRemoteCall(val user: String?)

// Sample Data to show it in the documentation.
private data class ExceptionLoggerClass(val log: String?)

// Sample fun to show it in the documentation.
private fun handleException(data: Failure<Nothing>) {}