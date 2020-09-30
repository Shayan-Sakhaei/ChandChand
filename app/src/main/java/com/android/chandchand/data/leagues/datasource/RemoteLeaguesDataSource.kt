package com.android.chandchand.data.leagues.datasource

import com.android.chandchand.data.leagues.api.LeaguesApi
import com.android.chandchand.data.leagues.entity.StandingsServerModel
import com.android.chandchand.domain.datasources.LeaguesDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteLeaguesDataSource @Inject constructor(
    private val api: LeaguesApi
) : LeaguesDataSource {
    override suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel> =
        api.getStandingsByLeague(leagueId)
}