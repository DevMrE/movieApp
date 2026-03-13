package com.kmp.movieapp.core.util.string

fun String.clearInput() = this.trim().replace("\\s+".toRegex(), " ")