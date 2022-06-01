package com.android.chandchand.presentation.predictions

import com.android.chandchand.domain.entities.PredictionsEntity
import com.android.chandchand.presentation.common.IState

data class PredictionsState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val homeTeamLogoUrl: String? = "",
    val awayTeamLogoUrl: String? = "",
    val date: String? = "",
    val time: String? = "",
    val predictions: PredictionsEntity? = null,
    val errorMessage: String? = null
) : IState
