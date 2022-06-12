package com.android.chandchand.presentation.model

import com.android.domain.entities.LiveFixtureEntity

data class LiveFixturesPerLeagueModels(
    val results: Int,
    val entities: List<LiveFixturesPerLeagueModel>
)

data class LiveFixturesPerLeagueModel(
    val leagueModel: LeagueModel,
    val fixtures: List<com.android.domain.entities.LiveFixtureEntity>,
    val isExpanded: Boolean = false
)