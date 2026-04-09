package com.kmp.series.data.model.response


import kotlinx.serialization.SerialName

data class EpisodeToAirDto(
    @SerialName("air_date")
    val airDate: String? = null,
    @SerialName("episode_number")
    val episodeNumber: Int? = null,
    @SerialName("episode_type")
    val episodeType: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("production_code")
    val productionCode: String? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("season_number")
    val seasonNumber: Int? = null,
    @SerialName("show_id")
    val showId: Int? = null,
    @SerialName("still_path")
    val stillPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null
)