package com.android.data.fixtures.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixturesServerModel(
    @Json(name = "api") val api: FixApi
)

@JsonClass(generateAdapter = true)
data class FixApi(
    @Json(name = "results") val results: Int,
    @Json(name = "fixtures") val fixtures: List<FixFixtures>
)

@JsonClass(generateAdapter = true)
data class FixFixtures(
    @Json(name = "fixture_id") val fixture_id: Int,
    @Json(name = "league_id") val league_id: Int? = 0,
    @Json(name = "league") val league: FixLeague,
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
    @Json(name = "homeTeam") val homeTeam: FixHomeTeam,
    @Json(name = "awayTeam") val awayTeam: FixAwayTeam,
    @Json(name = "goalsHomeTeam") val goalsHomeTeam: String? = "",
    @Json(name = "goalsAwayTeam") val goalsAwayTeam: String? = "",
    @Json(name = "score") val score: FixScore
)

@JsonClass(generateAdapter = true)
data class FixLeague(
    @Json(name = "name") val name: String? = "",
    @Json(name = "country") val country: String? = "",
    @Json(name = "logo") val logo: String? = "",
    @Json(name = "flag") val flag: String? = ""
)

@JsonClass(generateAdapter = true)
data class FixHomeTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

@JsonClass(generateAdapter = true)
data class FixAwayTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

@JsonClass(generateAdapter = true)
data class FixScore(
    @Json(name = "halftime") val halftime: String? = "",
    @Json(name = "fulltime") val fulltime: String? = "",
    @Json(name = "extratime") val extratime: String? = "",
    @Json(name = "penalty") val penalty: String? = ""
)
