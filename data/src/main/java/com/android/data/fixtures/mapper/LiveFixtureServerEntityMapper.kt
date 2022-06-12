package com.android.data.fixtures.mapper

import com.android.data.fixtures.entity.LiveFixFixtures
import com.android.domain.common.Mapper
import com.android.domain.entities.LiveFixtureEntity
import javax.inject.Inject

class LiveFixtureServerEntityMapper @Inject constructor(
    val liveFixEventsServerEntityMapper: LiveFixEventsServerEntityMapper
) : Mapper<LiveFixFixtures, LiveFixtureEntity> {
    override fun map(item: LiveFixFixtures): LiveFixtureEntity =
        LiveFixtureEntity(
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
            item.score.penalty,
            item.events.map {
                liveFixEventsServerEntityMapper.map(it)
            }
        )
}