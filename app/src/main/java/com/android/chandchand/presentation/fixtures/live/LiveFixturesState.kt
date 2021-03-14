package com.android.chandchand.presentation.fixtures.live

import com.android.chandchand.presentation.common.IState
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModels

data class LiveFixturesState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val liveFixtures: LiveFixturesPerLeagueModels = LiveFixturesPerLeagueModels(0, listOf()),
    val errorMessage: String? = null
) : IState