package com.android.chandchand.presentation.predictions

import androidx.compose.ui.graphics.Color
import com.android.chandchand.presentation.common.IState
import com.android.chandchand.presentation.ui.theme.LightSurfaceInfo
import com.android.domain.entities.PredictionsEntity
import com.android.domain.entities.Winner

data class PredictionsState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val homeTeamLogoUrl: String? = "",
    val awayTeamLogoUrl: String? = "",
    val date: String? = "",
    val time: String? = "",
    val predictions: PredictionsEntity? = null,
    val errorMessage: String? = null,
    val homeTeamColor: Color? = LightSurfaceInfo,
    val awayTeamColor: Color? = LightSurfaceInfo,
) : IState {
    val winnerTitle: String
        get() = humanReadableMatchWinner(predictions)

    val winnerLogoUrl: String?
        get() = when (predictions?.match_winner) {
            Winner.HOME -> {
                homeTeamLogoUrl
            }
            Winner.AWAY -> {
                awayTeamLogoUrl
            }
            else -> {
                null
            }
        }
}

enum class HomeAway { HOME, AWAY }

fun humanReadableMatchWinner(entity: PredictionsEntity?): String {
    return if (entity == null) "N/A"
    else when (entity.match_winner) {
        Winner.HOME -> {
            "${entity.home_team_name} برد "
        }
        Winner.HOME_DRAW -> {
            " یا مساوی ${entity.home_team_name} برد "
        }
        Winner.DRAW -> {
            "مساوی"
        }
        Winner.AWAY_DRAW -> {
            " یا مساوی ${entity.away_team_name} برد "
        }
        Winner.AWAY -> {
            "${entity.away_team_name} برد "
        }
        Winner.NOT_DEFINED -> {
            "N/A"
        }
    }
}