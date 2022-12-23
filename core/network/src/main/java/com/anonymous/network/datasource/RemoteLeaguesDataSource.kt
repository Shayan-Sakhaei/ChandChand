package com.anonymous.network.datasource

import com.anonymous.network.api.LeaguesApi
import com.anonymous.network.model.StandingsServerModel
import retrofit2.Response
import javax.inject.Inject

class RemoteLeaguesDataSource @Inject constructor(
    private val api: LeaguesApi
) : LeaguesDataSource {
    override suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel> =
        api.getStandingsByLeague(leagueId)
}