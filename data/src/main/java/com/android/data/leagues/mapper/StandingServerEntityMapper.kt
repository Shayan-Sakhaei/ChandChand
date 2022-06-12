package com.android.data.leagues.mapper

import com.android.data.leagues.entity.StdStandings
import com.android.domain.common.Mapper
import com.android.domain.entities.StandingEntity
import javax.inject.Inject

class StandingServerEntityMapper @Inject constructor(
) : Mapper<StdStandings, StandingEntity> {
    override fun map(item: StdStandings): StandingEntity =
        StandingEntity(
            item.rank,
            item.team_id,
            item.teamName,
            item.logo,
            item.group,
            item.forme,
            item.status,
            item.description,
            item.all.matchsPlayed,
            item.all.win,
            item.all.draw,
            item.all.lose,
            item.all.goalsFor,
            item.all.goalsAgainst,
            item.home.matchsPlayed,
            item.home.win,
            item.home.draw,
            item.home.lose,
            item.home.goalsFor,
            item.home.goalsAgainst,
            item.away.matchsPlayed,
            item.away.win,
            item.away.draw,
            item.away.lose,
            item.away.goalsFor,
            item.away.goalsAgainst,
            item.goalsDiff,
            item.points,
            item.lastUpdate
        )
}