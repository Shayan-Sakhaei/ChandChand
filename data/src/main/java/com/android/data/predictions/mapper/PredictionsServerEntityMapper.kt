package com.android.data.predictions.mapper

import com.android.data.predictions.entity.PredictionsServerModel
import com.android.domain.common.Mapper
import com.android.domain.entities.PredictionsEntity
import javax.inject.Inject

class PredictionsServerEntityMapper @Inject constructor(
) : Mapper<PredictionsServerModel, PredictionsEntity> {
    override fun map(item: PredictionsServerModel): PredictionsEntity =
        PredictionsEntity(
            item.api.predictions[0].match_winner,
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
        )
}