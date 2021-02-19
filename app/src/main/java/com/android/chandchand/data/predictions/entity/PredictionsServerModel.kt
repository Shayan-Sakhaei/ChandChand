package com.android.chandchand.data.predictions.entity

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class PredictionsServerModel(
    @Json(name = "api") val api: PredictApi
)

data class PredictApi(
    @Json(name = "results") val results: Int,
    @Json(name = "predictions") val predictions: List<PredictPredictions>
)

data class PredictPredictions(
    @Json(name = "match_winner") val match_winner: String? = "",
    @Json(name = "under_over") val under_over: String? = "",
    @Json(name = "goals_home") val goals_home: Double? = 0.0,
    @Json(name = "goals_away") val goals_away: Double? = 0.0,
    @Json(name = "advice") val advice: String? = "",
    @Json(name = "winning_percent") val winning_percent: PredictWinningPercent,
    @Json(name = "teams") val teams: PredictTeams,
    @Json(name = "h2h") val h2h: List<PredictH2h>,
    @Json(name = "comparison") val comparison: PredictComparison
)

data class PredictWinningPercent(
    @Json(name = "home") val home: String,
    @Json(name = "draws") val draws: String,
    @Json(name = "away") val away: String
)


data class PredictTeams(
    @Json(name = "home") val home: PredictHome,
    @Json(name = "away") val away: PredictAway
)

data class PredictH2h(
    @Json(name = "fixture_id") val fixture_id: Int? = 0,
    @Json(name = "league_id") val league_id: Int? = 0,
    @Json(name = "league") val league: PredictLeague,
    @Json(name = "event_date") val event_date: String? = "",
    @Json(name = "event_timestamp") val event_timestamp: Int? = 0,
    @Json(name = "firstHalfStart") val firstHalfStart: Int? = 0,
    @Json(name = "secondHalfStart") val secondHalfStart: Int? = 0,
    @Json(name = "round") val round: String? = "",
    @Json(name = "status") val status: String? = "",
    @Json(name = "statusShort") val statusShort: String? = "",
    @Json(name = "elapsed") val elapsed: Int? = 0,
    @Json(name = "venue") val venue: String? = "",
    @Json(name = "referee") val referee: String? = "",
    @Json(name = "homeTeam") val homeTeam: PredictHomeTeam,
    @Json(name = "awayTeam") val awayTeam: PredictAwayTeam,
    @Json(name = "goalsHomeTeam") val goalsHomeTeam: Int? = 0,
    @Json(name = "goalsAwayTeam") val goalsAwayTeam: Int? = 0,
    @Json(name = "score") val score: PredictScore
)

data class PredictLeague(
    @Json(name = "name") val name: String? = "",
    @Json(name = "country") val country: String? = "",
    @Json(name = "logo") val logo: String? = "",
    @Json(name = "flag") val flag: String? = ""
)

data class PredictHomeTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

data class PredictAwayTeam(
    @Json(name = "team_id") val team_id: Int? = 0,
    @Json(name = "team_name") val team_name: String? = "",
    @Json(name = "logo") val logo: String? = ""
)

data class PredictScore(
    @Json(name = "halftime") val halftime: String? = "",
    @Json(name = "fulltime") val fulltime: String? = "",
    @Json(name = "extratime") val extratime: String? = "",
    @Json(name = "penalty") val penalty: String? = ""
)

data class PredictComparison(
    @Json(name = "forme") val forme: PredictComparisonForme,
    @Json(name = "att") val att: PredictComparisonAtt,
    @Json(name = "def") val def: PredictComparisonDef,
    @Json(name = "fish_law") val fish_law: PredictComparisonFishLaw,
    @Json(name = "h2h") val h2h: PredictComparisonH2h,
    @Json(name = "goals_h2h") val goals_h2h: PredictComparisonGoalsH2h
)

data class PredictHome(
    @Json(name = "team_id") val team_id: Int,
    @Json(name = "team_name") val team_name: String,
    @Json(name = "last_5_matches") val last_5_matches: PredictLast5Matches,
    @Json(name = "all_last_matches") val all_last_matches: PredictAllLastMatches,
    @Json(name = "last_h2h") val last_h2h: PredictLastH2h
)

data class PredictAway(
    @Json(name = "team_id") val team_id: Int,
    @Json(name = "team_name") val team_name: String,
    @Json(name = "last_5_matches") val last_5_matches: PredictLast5Matches,
    @Json(name = "all_last_matches") val all_last_matches: PredictAllLastMatches,
    @Json(name = "last_h2h") val last_h2h: PredictLastH2h
)


data class PredictComparisonForme(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictComparisonAtt(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictComparisonDef(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictComparisonFishLaw(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictComparisonH2h(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictComparisonGoalsH2h(
    @Json(name = "home") val home: String? = "",
    @Json(name = "away") val away: String? = ""
)

data class PredictLast5Matches(
    @Json(name = "forme") val forme: String? = "",
    @Json(name = "att") val att: String? = "",
    @Json(name = "def") val def: String? = "",
    @Json(name = "goals") val goals: Int? = 0,
    @Json(name = "goals_avg") val goals_avg: Double? = 0.0,
    @Json(name = "goals_against") val goals_against: Int? = 0,
    @Json(name = "goals_against_avg") val goals_against_avg: Double? = 0.0
)

data class PredictAllLastMatches(
    @Json(name = "matchs") val matchs: PredictMatchs,
    @Json(name = "goals") val goals: PredictGoals,
    @Json(name = "goalsAvg") val goalsAvg: PredictGoalsAvg
)

data class PredictLastH2h(
    @Json(name = "played") val played: PredictPlayed,
    @Json(name = "wins") val wins: PredictWins,
    @Json(name = "draws") val draws: PredictDraws,
    @Json(name = "loses") val loses: PredictLoses
)

data class PredictMatchs(
    @Json(name = "matchsPlayed") val matchsPlayed: PredictMatchsPlayed,
    @Json(name = "wins") val wins: PredictWins,
    @Json(name = "draws") val draws: PredictDraws,
    @Json(name = "loses") val loses: PredictLoses
)

data class PredictGoals(
    @Json(name = "goalsFor") val goalsFor: PredictGoalsFor,
    @Json(name = "goalsAgainst") val goalsAgainst: PredictGoalsAgainst
)

data class PredictGoalsAvg(
    @Json(name = "goalsFor") val goalsFor: PredictGoalsFor,
    @Json(name = "goalsAgainst") val goalsAgainst: PredictGoalsAgainst
)

data class PredictPlayed(
    @Json(name = "home") val home: Int? = 0,
    @Json(name = "away") val away: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)


data class PredictWins(
    @Json(name = "home") val home: Int? = 0,
    @Json(name = "away") val away: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)

data class PredictDraws(
    @Json(name = "home") val home: Int? = 0,
    @Json(name = "away") val away: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)

data class PredictLoses(
    @Json(name = "home") val home: Int? = 0,
    @Json(name = "away") val away: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)

data class PredictMatchsPlayed(
    @Json(name = "home") val home: Int? = 0,
    @Json(name = "away") val away: Int? = 0,
    @Json(name = "total") val total: Int? = 0
)

data class PredictGoalsFor(
    @Json(name = "home") val home: Double? = 0.0,
    @Json(name = "away") val away: Double? = 0.0,
    @Json(name = "total") val total: Double? = 0.0
)

data class PredictGoalsAgainst(
    @Json(name = "home") val home: Double? = 0.0,
    @Json(name = "away") val away: Double? = 0.0,
    @Json(name = "total") val total: Double? = 0.0
)