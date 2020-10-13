package com.android.chandchand.presentation.model

import com.android.chandchand.domain.entities.FixtureEntity

data class FixturesPerLeagueModel(
    val leagueModel: LeagueModel,
    val fixtures: List<FixtureEntity>,
)