package com.anonymous.fixtures

import com.anonymous.fixtures.model.FixturesPerLeagueModel

data class DailyFixturesState(
    val fixtures: List<FixturesPerLeagueModel> = listOf(),
    val errorMessage: String? = null
)