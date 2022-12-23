package com.anonymous.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StandingsServerModel(
    @Json(name = "api") val api: StdApi
)

@JsonClass(generateAdapter = true)
data class StdApi(
    @Json(name = "results") val results: Int,
    @Json(name = "standings") val standings: List<List<StdStandings>>
)

@JsonClass(generateAdapter = true)
data class StdStandings(
    @Json(name = "rank") val rank: Int? = 0,
    @Json(name = "team_id") val team_id: Int,
    @Json(name = "teamName") val teamName: String? = "",
    @Json(name = "logo") val logo: String? = "",
    @Json(name = "group") val group: String? = "",
    @Json(name = "forme") val forme: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "description") val description: String? = "",
    @Json(name = "all") val all: StdAll,
    @Json(name = "home") val home: StdHome,
    @Json(name = "away") val away: StdAway,
    @Json(name = "goalsDiff") val goalsDiff: Int? = 0,
    @Json(name = "points") val points: Int? = 0,
    @Json(name = "lastUpdate") val lastUpdate: String? = ""
)

@JsonClass(generateAdapter = true)
data class StdAll(
    @Json(name = "matchsPlayed") val matchsPlayed: Int? = 0,
    @Json(name = "win") val win: Int? = 0,
    @Json(name = "draw") val draw: Int? = 0,
    @Json(name = "lose") val lose: Int? = 0,
    @Json(name = "goalsFor") val goalsFor: Int? = 0,
    @Json(name = "goalsAgainst") val goalsAgainst: Int? = 0
)

@JsonClass(generateAdapter = true)
data class StdHome(
    @Json(name = "matchsPlayed") val matchsPlayed: Int? = 0,
    @Json(name = "win") val win: Int? = 0,
    @Json(name = "draw") val draw: Int? = 0,
    @Json(name = "lose") val lose: Int? = 0,
    @Json(name = "goalsFor") val goalsFor: Int? = 0,
    @Json(name = "goalsAgainst") val goalsAgainst: Int? = 0
)

@JsonClass(generateAdapter = true)
data class StdAway(
    @Json(name = "matchsPlayed") val matchsPlayed: Int? = 0,
    @Json(name = "win") val win: Int? = 0,
    @Json(name = "draw") val draw: Int? = 0,
    @Json(name = "lose") val lose: Int? = 0,
    @Json(name = "goalsFor") val goalsFor: Int? = 0,
    @Json(name = "goalsAgainst") val goalsAgainst: Int? = 0
)
