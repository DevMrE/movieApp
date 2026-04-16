package com.kmp.movieapp.features.home.data.model.request.discover

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class SortByRequestDto {
    @SerialName("id.asc")
    TITLE_ASC,

    @SerialName("id.desc")
    TITLE_DESC,

    @SerialName("original_title.asc")
    ORIGINAL_TITLE_ASC,

    @SerialName("original_title.desc")
    ORIGINAL_TITLE_DESC,

    @SerialName("popularity.asc")
    POPULARITY_ASC,

    @SerialName("popularity.desc")
    POPULARITY_DESC,

    @SerialName("revenue.asc")
    REVENUE_ASC,

    @SerialName("revenue.desc")
    REVENUE_DESC,

    @SerialName("primary_release_date.asc")
    PRIMARY_RELEASE_DATE_ASC,

    @SerialName("primary_release_date.desc")
    PRIMARY_RELEASE_DATE_DESC,

    @SerialName("vote_average.asc")
    VOTE_AVERAGE_ASC,

    @SerialName("vote_average.desc")
    VOTE_AVERAGE_DESC,
}