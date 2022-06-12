package com.android.data.fixtures.mapper

import com.android.data.fixtures.entity.LiveFixEvents
import com.android.domain.common.Mapper
import com.android.domain.entities.LiveFixtureEventsEntity
import javax.inject.Inject

class LiveFixEventsServerEntityMapper @Inject constructor() :
    Mapper<LiveFixEvents, LiveFixtureEventsEntity> {
    override fun map(item: LiveFixEvents): LiveFixtureEventsEntity =
        LiveFixtureEventsEntity(
            item.elapsed,
            item.elapsed_plus,
            item.team_id,
            item.teamName,
            item.player_id,
            item.player,
            item.assist_id,
            item.assist,
            item.type,
            item.detail,
            item.comments
        )
}