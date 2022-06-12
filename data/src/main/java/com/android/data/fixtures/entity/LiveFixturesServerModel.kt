package com.android.data.fixtures.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveFixturesServerModel(
    @Json(name = "api") val api: LiveFixApi
)

@JsonClass(generateAdapter = true)
data class LiveFixApi(
    @Json(name = "results") val results: Int,
    @Json(name = "fixtures") val fixtures: List<LiveFixFixtures>
)

@JsonClass(generateAdapter = true)
data class LiveFixFixtures(
    @Json(name = "fixture_id") val fixture_id: Int? = 0,
    @Json(name = "league_id") val league_id: Int? = 0,
    @Json(name = "league") val league: LiveFixLeague,
    @Json(name = "event_date") val event_date: String? = "",
    @Json(name = "event_timestamp") val event_timestamp: Long? = 0,
    @Json(name = "firstHalfStart") val firstHalfStart: String? = "",
    @Json(name = "secondHalfStart") val secondHalfStart: String? = "",
    @Json(name = "round") val round: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "statusShort") val statusShort: String? = "",
    @Json(name = "elapsed") val elapsed: Int? = 0,
    @Json(name = "venue") val venue: String? = "",
    @Json(name = "referee") val referee: String? = "",
    @Json(name = "homeTeam") val homeTeam: LiveFixHomeTeam,
    @Json(name = "awayTeam") val awayTeam: LiveFixAwayTeam,
    @Json(name = "goalsHomeTeam") val goalsHomeTeam: String? = "",
    @Json(name = "goalsAwayTeam") val goalsAwayTeam: String? = "",
    @Json(name = "score") val score: LiveFixScore,
    @Json(name = "events") val events: List<LiveFixEvents>
)

@JsonClass(generateAdapter = true)
data class LiveFixLeague(
    @Json(name = "name") val name: String? = "",
    @Json(name = "country") val country: String? = "",
    @Json(name = "logo") val logo: String? = "",
    @Json(name = "flag") val flag: String? = ""
)

@JsonClass(generateAdapter = true)
data class LiveFixHomeTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

@JsonClass(generateAdapter = true)
data class LiveFixAwayTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

@JsonClass(generateAdapter = true)
data class LiveFixScore(
    @Json(name = "halftime") val halftime: String? = "",
    @Json(name = "fulltime") val fulltime: String? = "",
    @Json(name = "extratime") val extratime: String? = "",
    @Json(name = "penalty") val penalty: String? = ""
)

@JsonClass(generateAdapter = true)
data class LiveFixEvents(
    @Json(name = "elapsed") val elapsed: Int? = 0,
    @Json(name = "elapsed_plus") val elapsed_plus: String? = "",
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "teamName") val teamName: String? = "",
    @Json(name = "player_id") val player_id: String? = "",
    @Json(name = "player") val player: String? = "",
    @Json(name = "assist_id") val assist_id: String? = "",
    @Json(name = "assist") val assist: String? = "",
    @Json(name = "type") val type: String? = "",
    @Json(name = "detail") val detail: String? = "",
    @Json(name = "comments") val comments: String? = ""
)