package com.anonymous.data.mapper

import com.anonymous.common.mapper.Mapper
import com.anonymous.data.model.LiveFixtureEventsEntity
import com.anonymous.network.model.LiveFixEvents
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