package com.anonymous.network.datasource

import com.anonymous.network.model.StandingsServerModel

interface LeaguesDataSource {
    suspend fun getStandingsByLeagueId(leagueId: Int): Result<StandingsServerModel>
}