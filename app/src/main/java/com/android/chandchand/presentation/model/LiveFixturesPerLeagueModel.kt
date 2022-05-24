package com.android.chandchand.presentation.model

import com.android.chandchand.domain.entities.LiveFixtureEntity

data class LiveFixturesPerLeagueModels(
    val results: Int,
    val entities: List<LiveFixturesPerLeagueModel>
)

data class LiveFixturesPerLeagueModel(
    val leagueModel: LeagueModel,
    val fixtures: List<LiveFixtureEntity>,
    val isExpanded: Boolean = false
)