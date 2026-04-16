package com.kmp.movieapp.features.home.domain.mapper

interface UiMapper<UiData, DomainData> {

    fun toDomainData(uiData: UiData): DomainData

    fun toUiData(domainData: DomainData): UiData
}
