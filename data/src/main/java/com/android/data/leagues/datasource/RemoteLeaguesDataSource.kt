package com.android.data.leagues.datasource

import com.android.data.datasources.LeaguesDataSource
import com.android.data.leagues.api.LeaguesApi
import com.android.data.leagues.entity.StandingsServerModel
import retrofit2.Response
import javax.inject.Inject

class RemoteLeaguesDataSource @Inject constructor(
    private val api: LeaguesApi
) : LeaguesDataSource {
    override suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel> =
        api.getStandingsByLeague(leagueId)
}