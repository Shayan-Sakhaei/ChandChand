package com.android.chandchand.presentation.leagues

import com.android.domain.entities.StandingEntity
import com.android.chandchand.presentation.common.IState

data class LeaguesState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val standings: List<com.android.domain.entities.StandingEntity> = listOf(),
    val errorMessage: String? = null
) : IState