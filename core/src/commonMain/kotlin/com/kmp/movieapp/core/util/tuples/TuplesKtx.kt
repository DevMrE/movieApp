package com.kmp.movieapp.core.util.tuples

data class Triple<F, S, T>(val first: F, val second: S, val third: T)

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

data class Quintuple<A, B, C, D, E>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E)

data class Sextuple<A, B, C, D, E, F>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E, val sixth: F)

data class Septuple<A, B, C, D, E, F, G>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E, val sixth: F, val seventh: G)

// 1 -> 2 (A, B) → Pair
infix fun <A, B> A.with(second: B): Pair<A, B> = Pair(this, second)

// 2 -> 3 (A, B) + C → Triple<A,B,C>
infix fun <A, B, C> Pair<A, B>.with(third: C): Triple<A, B, C> = Triple(first, second, third)

// 3 -> 4 (A, B, C) + D → Quadruple<A,B,C,D>
infix fun <A, B, C, D> Triple<A, B, C>.with(fourth: D): Quadruple<A, B, C, D> = Quadruple(first, second, third, fourth)

// 4 -> 5 (A, B, C, D) + E → Quintuple<A,B,C,D,E>
infix fun <A, B, C, D, E> Quadruple<A, B, C, D>.with(fifth: E): Quintuple<A, B, C, D, E> = Quintuple(first, second, third, fourth, fifth)

// 5 -> 6 (A, B, C, D, E) + F → Sextuple<A,B,C,D,E,F>
infix fun <A, B, C, D, E, F> Quintuple<A, B, C, D, E>.with(sixth: F): Sextuple<A, B, C, D, E, F> = Sextuple(first, second, third, fourth, fifth, sixth)

// 6 -> 7 (A, B, C, D, E, F) + G → Septuple<A,B,C,D,E,F,G>
infix fun <A, B, C, D, E, F, G> Sextuple<A, B, C, D, E, F>.with(seventh: G): Septuple<A, B, C, D, E, F, G> = Septuple(first, second, third, fourth, fifth, sixth, seventh)