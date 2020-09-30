package com.android.chandchand.domain.datasources

import com.android.chandchand.data.leagues.entity.StandingsServerModel
import retrofit2.Response

interface LeaguesDataSource {
    suspend fun getStandingsByLeagueId(leagueId: Int): Response<StandingsServerModel>
}