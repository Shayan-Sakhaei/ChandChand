package com.android.domain.entities

data class FixtureEntity(
    val id: Int,
    val league_id: Int?,
    val league_name: String?,
    val league_country: String?,
    val league_logo: String?,
    val date: String?,
    val timestamp: Long?,
    val round: String?,
    val status: String?,
    val status_short: String?,
    val elapsed: Int?,
    val venue: String?,
    val referee: String?,
    val home_team_id: Int?,
    val home_team_name: String?,
    val home_team_logo: String?,
    val away_team_id: Int?,
    val away_team_name: String?,
    val away_team_logo: String?,
    val goals_home: String?,
    val goals_away: String?,
    val score_halftime: String?,
    val score_fulltime: String?,
    val score_extratime: String?,
    val score_penalty: String?
)