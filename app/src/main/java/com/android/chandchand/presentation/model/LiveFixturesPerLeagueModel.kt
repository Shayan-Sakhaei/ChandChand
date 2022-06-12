package com.android.chandchand.presentation.model

import com.android.domain.entities.LiveFixtureEntity

data class LiveFixturesPerLeagueModel(
    val leagueModel: LeagueModel,
    val fixtures: List<LiveFixtureEntity>,
    val isExpanded: Boolean = false
)