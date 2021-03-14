package com.android.chandchand.presentation.fixtures

import com.android.chandchand.presentation.common.IState
import com.android.chandchand.presentation.model.FixturesPerLeagueModel

data class FixturesState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val fixtures: List<FixturesPerLeagueModel> = listOf(),
    val errorMessage: String? = null
) : IState