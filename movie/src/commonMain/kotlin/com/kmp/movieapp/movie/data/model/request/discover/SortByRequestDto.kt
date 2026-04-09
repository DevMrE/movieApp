package com.kmp.movieapp.movie.data.model.request.discover

internal enum class SortByRequestDto(val sortName: String) {
    TITLE_ASC("id.asc"),
    TITLE_DESC("id.desc"),
    ORIGINAL_TITLE_ASC("original_title.asc"),
    ORIGINAL_TITLE_DESC("original_title.desc"),
    POPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    REVENUE_ASC("revenue.asc"),
    REVENUE_DESC("revenue.desc"),
    PRIMARY_RELEASE_DATE_ASC("primary_release_date.asc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc"),
    VOTE_AVERAGE_ASC("vote_average.asc"),
    VOTE_AVERAGE_DESC("vote_average.desc"),
}