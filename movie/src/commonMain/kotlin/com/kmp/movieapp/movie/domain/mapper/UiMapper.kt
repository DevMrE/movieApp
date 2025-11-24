package com.kmp.movieapp.movie.domain.mapper

interface UiMapper<UiData, DomainData> {

    fun toDomainData(uiData: UiData): DomainData

    fun toUiData(domainData: DomainData): UiData
}
