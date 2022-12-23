package com.anonymous.leagues

import com.anonymous.data.model.StandingEntity
import com.anonymous.ui.model.IState

data class StandingsState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val standings: List<StandingEntity> = listOf(),
    val errorMessage: String? = null
) : IState