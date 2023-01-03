package com.anonymous.network.datasource

import com.anonymous.network.api.LeaguesApi
import com.anonymous.network.model.StandingsServerModel
import javax.inject.Inject

class RemoteLeaguesDataSource @Inject constructor(
    private val api: LeaguesApi
) : LeaguesDataSource {
    override suspend fun getStandingsByLeagueId(leagueId: Int): Result<StandingsServerModel> =
        api.getStandingsByLeague(leagueId)
}