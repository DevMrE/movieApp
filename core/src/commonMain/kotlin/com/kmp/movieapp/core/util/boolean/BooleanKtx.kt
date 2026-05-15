package com.kmp.movieapp.core.util.boolean

/**
 * Checks if the nullable Int is greater than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Int is not null and greater than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Int? = 10
 * val result = value.isGreaterThan(5) // returns true
 *
 * val nullValue: Int? = null
 * val nullResult = nullValue.isGreaterThan(5) // returns false
 * ```
 */
fun Int?.isGreaterThan(compareValue: Int): Boolean {
    return this != null && this > compareValue
}

/**
 * Checks if the nullable Int is greater than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Int is not null and greater than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Int? = 10
 * val result1 = value.isGreaterThanOrEquals(10) // returns true
 * val result2 = value.isGreaterThanOrEquals(15) // returns false
 *
 * val nullValue: Int? = null
 * val nullResult = nullValue.isGreaterThanOrEquals(5) // returns false
 * ```
 */
fun Int?.isGreaterThanOrEquals(compareValue: Int): Boolean {
    return this != null && this >= compareValue
}

/**
 * Checks if the nullable Int is less than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Int is not null and less than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Int? = 5
 * val result = value.isLessThan(10) // returns true
 *
 * val nullValue: Int? = null
 * val nullResult = nullValue.isLessThan(10) // returns false
 * ```
 */
fun Int?.isLessThan(compareValue: Int): Boolean {
    return this != null && this < compareValue
}

/**
 * Checks if the nullable Int is less than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Int is not null and less than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Int? = 10
 * val result1 = value.isLessThanOrEquals(10) // returns true
 * val result2 = value.isLessThanOrEquals(5) // returns false
 *
 * val nullValue: Int? = null
 * val nullResult = nullValue.isLessThanOrEquals(10) // returns false
 * ```
 */
fun Int?.isLessThanOrEquals(compareValue: Int): Boolean {
    return this != null && this <= compareValue
}

/**
 * Checks if the nullable Long is greater than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Long is not null and greater than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Long? = 1000L
 * val result = value.isGreaterThan(500L) // returns true
 *
 * val nullValue: Long? = null
 * val nullResult = nullValue.isGreaterThan(500L) // returns false
 * ```
 */
fun Long?.isGreaterThan(compareValue: Long): Boolean {
    return this != null && this > compareValue
}

/**
 * Checks if the nullable Long is greater than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Long is not null and greater than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Long? = 1000L
 * val result1 = value.isGreaterThanOrEquals(1000L) // returns true
 * val result2 = value.isGreaterThanOrEquals(1500L) // returns false
 *
 * val nullValue: Long? = null
 * val nullResult = nullValue.isGreaterThanOrEquals(500L) // returns false
 * ```
 */
fun Long?.isGreaterThanOrEquals(compareValue: Long): Boolean {
    return this != null && this >= compareValue
}

/**
 * Checks if the nullable Long is less than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Long is not null and less than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Long? = 500L
 * val result = value.isLessThan(1000L) // returns true
 *
 * val nullValue: Long? = null
 * val nullResult = nullValue.isLessThan(1000L) // returns false
 * ```
 */
fun Long?.isLessThan(compareValue: Long): Boolean {
    return this != null && this < compareValue
}

/**
 * Checks if the nullable Long is less than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Long is not null and less than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Long? = 1000L
 * val result1 = value.isLessThanOrEquals(1000L) // returns true
 * val result2 = value.isLessThanOrEquals(500L) // returns false
 *
 * val nullValue: Long? = null
 * val nullResult = nullValue.isLessThanOrEquals(1000L) // returns false
 * ```
 */
fun Long?.isLessThanOrEquals(compareValue: Long): Boolean {
    return this != null && this <= compareValue
}

/**
 * Checks if the nullable Float is greater than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Float is not null and greater than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Float? = 10.5f
 * val result = value.isGreaterThan(5.2f) // returns true
 *
 * val nullValue: Float? = null
 * val nullResult = nullValue.isGreaterThan(5.2f) // returns false
 * ```
 */
fun Float?.isGreaterThan(compareValue: Float): Boolean {
    return this != null && this > compareValue
}

/**
 * Checks if the nullable Float is greater than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Float is not null and greater than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Float? = 10.5f
 * val result1 = value.isGreaterThanOrEquals(10.5f) // returns true
 * val result2 = value.isGreaterThanOrEquals(15.7f) // returns false
 *
 * val nullValue: Float? = null
 * val nullResult = nullValue.isGreaterThanOrEquals(5.2f) // returns false
 * ```
 */
fun Float?.isGreaterThanOrEquals(compareValue: Float): Boolean {
    return this != null && this >= compareValue
}

/**
 * Checks if the nullable Float is less than the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Float is not null and less than compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Float? = 5.2f
 * val result = value.isLessThan(10.5f) // returns true
 *
 * val nullValue: Float? = null
 * val nullResult = nullValue.isLessThan(10.5f) // returns false
 * ```
 */
fun Float?.isLessThan(compareValue: Float): Boolean {
    return this != null && this < compareValue
}

/**
 * Checks if the nullable Float is less than or equal to the specified value.
 *
 * @param compareValue The value to compare against
 * @return true if this Float is not null and less than or equal to compareValue, false otherwise
 *
 * Example:
 * ```
 * val value: Float? = 10.5f
 * val result1 = value.isLessThanOrEquals(10.5f) // returns true
 * val result2 = value.isLessThanOrEquals(5.2f) // returns false
 *
 * val nullValue: Float? = null
 * val nullResult = nullValue.isLessThanOrEquals(10.5f) // returns false
 * ```
 */
fun Float?.isLessThanOrEquals(compareValue: Float): Boolean {
    return this != null && this <= compareValue
}



/**
 * Returns the string itself if it's not null or blank, otherwise returns the provided alternative string.
 *
 * @param otherwise The alternative string to return if this string is null or blank
 * @return This string if it's not null or blank, otherwise the provided alternative string
 *
 * Example:
 * ```
 * val name: String? = "John"
 * val result1 = name.orIfEmpty("Unknown") // returns "John"
 *
 * val emptyName: String? = ""
 * val result2 = emptyName.orIfEmpty("Unknown") // returns "Unknown"
 *
 * val nullName: String? = null
 * val result3 = nullName.orIfEmpty("Unknown") // returns "Unknown"
 * ```
 */
fun String?.orIfEmpty(otherwise: String?): String? {
    if (this == null || this.isBlank()) {
        return otherwise
    }
    return this
}

/**
 * Checks if the nullable Boolean is true.
 *
 * @return true if this Boolean is true, false if it's false or null
 *
 * Example:
 * ```
 * val flag: Boolean? = true
 * val result1 = flag.isTrue // returns true
 *
 * val falseFlag: Boolean? = false
 * val result2 = falseFlag.isTrue // returns false
 *
 * val nullFlag: Boolean? = null
 * val result3 = nullFlag.isTrue // returns false
 * ```
 */
val Boolean?.isTrue: Boolean
    get() = this == true

/**
 * Checks if the nullable Boolean is true or null (i.e., not false).
 *
 * @return true if this Boolean is true or null, false if it's false
 *
 * Example:
 * ```
 * val flag: Boolean? = true
 * val result1 = flag.isTrueOrNull // returns true
 *
 * val falseFlag: Boolean? = false
 * val result2 = falseFlag.isTrueOrNull // returns false
 *
 * val nullFlag: Boolean? = null
 * val result3 = nullFlag.isTrueOrNull // returns true
 * ```
 */
val Boolean?.isTrueOrNull: Boolean
    get() = this != false

/**
 * Checks if the nullable Boolean is false.
 *
 * @return true if this Boolean is false, false if it's true or null
 *
 * Example:
 * ```
 * val flag: Boolean? = false
 * val result1 = flag.isFalse // returns true
 *
 * val trueFlag: Boolean? = true
 * val result2 = trueFlag.isFalse // returns false
 *
 * val nullFlag: Boolean? = null
 * val result3 = nullFlag.isFalse // returns false
 * ```
 */
val Boolean?.isFalse: Boolean
    get() = this == false

/**
 * Checks if the nullable Boolean is false or null (i.e., not true).
 *
 * @return true if this Boolean is false or null, false if it's true
 *
 * Example:
 * ```
 * val flag: Boolean? = false
 * val result1 = flag.isFalseOrNull // returns true
 *
 * val trueFlag: Boolean? = true
 * val result2 = trueFlag.isFalseOrNull // returns false
 *
 * val nullFlag: Boolean? = null
 * val result3 = nullFlag.isFalseOrNull // returns true
 * ```
 */
val Boolean?.isFalseOrNull: Boolean
    get() = this != true

/**
 * Checks if the nullable Boolean is null.
 *
 * @return true if this Boolean is null, false otherwise
 *
 * Example:
 * ```
 * val flag: Boolean? = true
 * val result1 = flag.isNull // returns false
 *
 * val falseFlag: Boolean? = false
 * val result2 = falseFlag.isNull // returns false
 *
 * val nullFlag: Boolean? = null
 * val result3 = nullFlag.isNull // returns true
 * ```
 */
val Boolean?.isNull: Boolean
    get() = this == null
