package com.kmp.movieapp.movie.domain.mapper

interface DataMapper<Data, Domain> {

    fun toData(domain: Domain): Data

    fun toDomain(data: Data): Domain
}