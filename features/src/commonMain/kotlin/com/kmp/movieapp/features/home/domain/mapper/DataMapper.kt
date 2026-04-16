package com.kmp.movieapp.features.home.domain.mapper

interface DataMapper<Data, Domain> {

    fun toData(domain: Domain): Data

    fun toDomain(data: Data): Domain
}