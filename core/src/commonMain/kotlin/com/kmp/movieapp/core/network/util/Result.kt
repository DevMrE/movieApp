package com.kmp.movieapp.core.network.util

import com.kmp.movieapp.core.network.util.Result.Failure
import com.kmp.movieapp.core.network.util.Result.Success

/**
 * Represents the result of an operation that can either succeed with [SuccessData]
 * or fail with optional fallback data of type [FallbackData].
 *
 * This abstraction enables:
 * - explicit success handling
 * - transformation of fallback data into a shared model
 * - fallback chaining without explicit branching (`when`)
 * - resolving the result into concrete values using [onSuccess] and [onFailure]
 *
 * [Success] contains the resulting value.
 * [Failure] contains optional metadata such as a message, throwable, nested cause,
 * and typed fallback data.
 *
 * ---
 *
 * ## Basic usage
 *
 * ```kotlin
 * fun loadData(): Result<String, Unit> {
 *     return Result.Success("data")
 * }
 *
 * loadData()
 *     .onSuccess { value ->
 *         println("Loaded: $value")
 *     }
 *     .onFailure { failure ->
 *         println("Failed: ${failure.message}")
 *     }
 * ```
 *
 * ---
 *
 * ## Accessing success value directly
 *
 * ```kotlin
 * val value: String? = loadData().getOrNull()
 * ```
 *
 * ---
 *
 * ## Transforming success values
 *
 * ```kotlin
 * data class RemoteModel(val raw: String)
 * data class DomainModel(val value: String)
 *
 * fun loadRemote(): Result<RemoteModel, Unit> =
 *     Result.Success(RemoteModel("raw"))
 *
 * val result: Result<DomainModel, Unit> =
 *     loadRemote().mapOnSuccess { remote ->
 *         DomainModel(remote.raw)
 *     }
 * ```
 *
 * ---
 *
 * ## Transforming fallback values
 *
 * ```kotlin
 * sealed interface RemoteError {
 *     data object NotFound : RemoteError
 *     data object Timeout : RemoteError
 * }
 *
 * sealed interface DomainError {
 *     data object NotFound : DomainError
 *     data object Unavailable : DomainError
 * }
 *
 * fun RemoteError.toDomainError(): DomainError {
 *     return when (this) {
 *         RemoteError.NotFound -> DomainError.NotFound
 *         RemoteError.Timeout -> DomainError.Unavailable
 *     }
 * }
 *
 * val result: Result<String, DomainError> =
 *     Result.Failure<String>(
 *         value = RemoteError.Timeout
 *     ).onError { error ->
 *         error.toDomainError()
 *     }
 * ```
 *
 * ---
 *
 * ## Remote → Local fallback example
 *
 * Both operations must use the same [FallbackData] type before [mapOnFailure]
 * can be used in a single chain.
 *
 * ```kotlin
 * sealed interface DataError {
 *     data object NotFound : DataError
 *     data object Unavailable : DataError
 *     data object Unknown : DataError
 * }
 *
 * data class RemoteModel(val value: String)
 * data class LocalModel(val value: String)
 * data class DomainModel(val value: String)
 *
 * fun RemoteModel.toDomain(): DomainModel = DomainModel(value)
 * fun LocalModel.toDomain(): DomainModel = DomainModel(value)
 *
 * fun loadRemote(): Result<RemoteModel, DataError> =
 *     Result.Failure(value = DataError.Unavailable)
 *
 * fun loadLocal(): Result<LocalModel, DataError> =
 *     Result.Success(LocalModel("cached"))
 *
 * val result: Result<DomainModel, DataError> =
 *     loadRemote()
 *         .mapOnSuccess { it.toDomain() }
 *         .fallbackOnError {
 *             loadLocal().mapOnSuccess { it.toDomain() }
 *         }
 * ```
 *
 * ---
 *
 * ## Resolving result into emitted data
 *
 * ```kotlin
 * data class RepositoryData<T>(
 *     val data: T? = null,
 *     val error: Any? = null
 * )
 *
 * suspend fun emitData(emit: suspend (RepositoryData<String>) -> Unit) {
 *     loadRemote()
 *         .mapOnSuccess { it.value }
 *         .onError { it } // optional mapping
 *         .fallbackOnError {
 *             loadLocal().mapOnSuccess { it.value }
 *         }
 *         .onSuccess { value ->
 *             emit(RepositoryData(data = value))
 *         }
 *         .onFailure { failure ->
 *             emit(RepositoryData(error = failure.value))
 *         }
 * }
 * ```
 *
 * ---
 *
 * @param SuccessData the type returned for a successful operation
 * @param FallbackData the typed fallback value used for failed operations.
 * Use [Unit] when no fallback data is required.
 */
sealed class Result<out SuccessData, out FallbackData> {

    /**
     * Returns the failure instance if this is a [Failure], otherwise `null`.
     */
    val failure: Failure<FallbackData>?
        get() = this as? Failure

    /**
     * Represents a successful result.
     */
    class Success<out SuccessData>(
        val value: SuccessData
    ) : Result<SuccessData, Nothing>()

    /**
     * Represents a failed result.
     */
    open class Failure<out FallbackData>(
        val message: String? = "",
        val throwable: Throwable? = null,
        val cause: Failure<Any>? = null,
        val value: FallbackData? = null
    ) : Result<Nothing, FallbackData>() {

        override fun toString(): String {
            return "Failure(\n" +
                    "value=$value\n" +
                    "message=$message\n" +
                    "throwable=$throwable\n" +
                    "cause=$cause\n" +
                    ")"
        }
    }
}

/**
 * Returns the success value if present, otherwise `null`.
 */
fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.getOrNull(): SuccessData? {
    return when (this) {
        is Success -> value
        is Failure -> null
    }
}

/**
 * Executes [block] only if this result is [Success].
 * Intended for side effects such as emitting data or logging.
 */
inline fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.onSuccess(
    block: (SuccessData) -> Unit
): Result<SuccessData, FallbackData> {
    if (this is Success) block(value)
    return this
}

/**
 * Executes [block] only if this result is [Failure].
 * Intended for side effects such as emitting error states.
 */
inline fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.onFailure(
    block: (Failure<FallbackData>) -> Unit
): Result<SuccessData, FallbackData> {
    if (this is Failure) block(this)
    return this
}

/**
 * Transforms the success value if this result is [Success].
 */
inline fun <SuccessData, FallbackData, MappedSuccessData> Result<SuccessData, FallbackData>.mapOnSuccess(
    block: (SuccessData) -> MappedSuccessData
): Result<MappedSuccessData, FallbackData> {
    return when (this) {
        is Success -> Success(block(value))
        is Failure -> this
    }
}

/**
 * Transforms the success value into another [Result] if this result is [Success].
 */
inline fun <SuccessData, FallbackData, MappedSuccessData> Result<SuccessData, FallbackData>.flatMap(
    block: (SuccessData) -> Result<MappedSuccessData, FallbackData>
): Result<MappedSuccessData, FallbackData> {
    return when (this) {
        is Success -> block(value)
        is Failure -> this
    }
}

/**
 * Transforms the fallback value if this result is [Failure].
 * Intended for mapping source-specific fallback data into a shared model.
 */
inline fun <SuccessData, FallbackData, MappedFallbackData> Result<SuccessData, FallbackData>.onError(
    block: (FallbackData) -> MappedFallbackData
): Result<SuccessData, MappedFallbackData> {
    return when (this) {
        is Success -> Success(value)
        is Failure -> Failure(
            message = message,
            throwable = throwable,
            cause = cause,
            value = value?.let(block)
        )
    }
}

/**
 * Executes the given fallback [block] only if this result is [Failure].
 * Allows chaining alternative operations (e.g. remote → local fallback).
 *
 * Both operations must use the same [FallbackData] type.
 */
inline fun <SuccessData, FallbackData> Result<SuccessData, FallbackData>.mapOnFailure(
    block: (Failure<FallbackData>) -> Result<SuccessData, FallbackData>
): Result<SuccessData, FallbackData> {
    return when (this) {
        is Success -> this
        is Failure -> block(this)
    }
}