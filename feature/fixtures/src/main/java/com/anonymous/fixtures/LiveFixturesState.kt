package com.anonymous.fixtures

import com.anonymous.fixtures.model.LiveFixturesPerLeagueModels
import com.anonymous.ui.model.IState

data class LiveFixturesState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val liveFixtures: LiveFixturesPerLeagueModels = LiveFixturesPerLeagueModels(0, listOf()),
    val errorMessage: String? = null
) : IState