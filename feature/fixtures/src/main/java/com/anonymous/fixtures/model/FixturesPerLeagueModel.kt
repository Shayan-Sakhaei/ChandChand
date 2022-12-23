package com.anonymous.fixtures.model

import com.anonymous.data.model.FixtureEntity

data class FixturesPerLeagueModel(
    val leagueHeaderModel: LeagueHeaderModel,
    val fixtures: List<FixtureEntity>,
    val isExpanded: Boolean = false
)