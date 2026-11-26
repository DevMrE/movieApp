package com.kmp.movieapp.search.util

fun String.validateSearch() = this.trim().replace("\\s+".toRegex(), " ")
