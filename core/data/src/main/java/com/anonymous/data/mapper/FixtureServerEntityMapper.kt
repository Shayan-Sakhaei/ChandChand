package com.anonymous.data.mapper

import com.anonymous.common.mapper.Mapper
import com.anonymous.data.model.FixtureEntity
import com.anonymous.network.model.FixFixtures
import javax.inject.Inject

class FixtureServerEntityMapper @Inject constructor(
) : Mapper<FixFixtures, FixtureEntity> {
    override fun map(item: FixFixtures): FixtureEntity =
        FixtureEntity(
            item.fixture_id,
            item.league_id,
            item.league.name,
            item.league.country,
            item.league.logo,
            item.event_date,
            item.event_timestamp,
            item.round,
            item.status,
            item.statusShort,
            item.elapsed,
            item.venue,
            item.referee,
            item.homeTeam.team_id,
            item.homeTeam.team_name,
            item.homeTeam.logo,
            item.awayTeam.team_id,
            item.awayTeam.team_name,
            item.awayTeam.logo,
            item.goalsHomeTeam,
            item.goalsAwayTeam,
            item.score.halftime,
            item.score.fulltime,
            item.score.extratime,
            item.score.penalty
        )
}