package com.android.chandchand.presentation.model

data class DailyFixturesState(
    val fixtures: List<FixturesPerLeagueModel> = listOf(),
    val errorMessage: String? = null
)