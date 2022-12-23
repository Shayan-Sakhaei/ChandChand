package com.anonymous.network.datasource

import com.anonymous.network.model.StandingsServerModel
import retrofit2.Response

interface LeaguesDataSource {
    suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel>
}