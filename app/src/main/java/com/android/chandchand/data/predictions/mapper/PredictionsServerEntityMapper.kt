package com.android.chandchand.data.predictions.mapper

import com.android.chandchand.data.common.Mapper
import com.android.chandchand.data.predictions.entity.PredictionsServerModel
import com.android.chandchand.domain.entities.PredictionsEntity
import com.android.chandchand.presentation.predictions.Winner
import com.android.chandchand.presentation.utils.getDigits
import javax.inject.Inject

class PredictionsServerEntityMapper @Inject constructor(
) : Mapper<PredictionsServerModel, PredictionsEntity> {
    override fun map(item: PredictionsServerModel): PredictionsEntity =
        PredictionsEntity(
            winner(item),
            item.api.predictions[0].under_over,
            item.api.predictions[0].goals_home,
            item.api.predictions[0].goals_away,
            item.api.predictions[0].advice,
            item.api.predictions[0].winning_percent.home,
            item.api.predictions[0].winning_percent.draws,
            item.api.predictions[0].winning_percent.away,
            item.api.predictions[0].teams.home.team_id,
            item.api.predictions[0].teams.home.team_name,
            item.api.predictions[0].teams.away.team_id,
            item.api.predictions[0].teams.away.team_name,
            item.api.predictions[0].teams.home.last_h2h.played.home,
            item.api.predictions[0].teams.home.last_h2h.wins.home,
            item.api.predictions[0].teams.home.last_h2h.draws.home,
            item.api.predictions[0].teams.home.last_h2h.loses.home,
            item.api.predictions[0].teams.away.last_h2h.played.home,
            item.api.predictions[0].teams.away.last_h2h.wins.home,
            item.api.predictions[0].teams.away.last_h2h.draws.home,
            item.api.predictions[0].teams.away.last_h2h.loses.home,
            item.api.predictions[0].comparison.forme.home,
            item.api.predictions[0].comparison.att.home,
            item.api.predictions[0].comparison.def.home,
            item.api.predictions[0].comparison.fish_law.home,
            item.api.predictions[0].comparison.h2h.home,
            item.api.predictions[0].comparison.goals_h2h.home,
            item.api.predictions[0].comparison.forme.away,
            item.api.predictions[0].comparison.att.away,
            item.api.predictions[0].comparison.def.away,
            item.api.predictions[0].comparison.fish_law.away,
            item.api.predictions[0].comparison.h2h.away,
            item.api.predictions[0].comparison.goals_h2h.away,
            item.api.predictions[0].comparison.forme.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.att.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.def.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.fish_law.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.h2h.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.goals_h2h.home?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.forme.away?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.att.away?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.def.away?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.fish_law.away?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.h2h.away?.getDigits()?.toInt(),
            item.api.predictions[0].comparison.goals_h2h.away?.getDigits()?.toInt()
        )
}

fun winner(model: PredictionsServerModel): Winner {
    val winner = model.api.predictions[0].match_winner
    return if (winner == null) Winner.NOT_DEFINED
    else when (model.api.predictions[0].match_winner) {
        "1" -> {
            Winner.HOME
        }
        "1 N" -> {
            Winner.HOME_DRAW
        }
        "N" -> {
            Winner.DRAW
        }
        "N 2" -> {
            Winner.AWAY_DRAW
        }
        "2" -> {
            Winner.AWAY
        }
        else -> {
            Winner.NOT_DEFINED
        }
    }
}

