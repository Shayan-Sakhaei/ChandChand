package com.android.chandchand.presentation.model

import com.android.domain.entities.FixtureEntity

data class FixturesPerLeagueModel(
    val leagueModel: LeagueModel,
    val fixtures: List<FixtureEntity>,
    val isExpanded: Boolean = false
)