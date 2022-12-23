package com.anonymous.fixtures.model

import com.anonymous.data.model.LiveFixtureEntity

data class LiveFixturesPerLeagueModel(
    val leagueHeaderModel: LeagueHeaderModel,
    val fixtures: List<LiveFixtureEntity>,
    val isExpanded: Boolean = false
)