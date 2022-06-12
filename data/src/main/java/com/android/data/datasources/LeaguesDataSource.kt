package com.android.data.datasources

import com.android.data.leagues.entity.StandingsServerModel
import retrofit2.Response

interface LeaguesDataSource {
    suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel>
}