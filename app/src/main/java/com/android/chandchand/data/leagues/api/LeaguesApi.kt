package com.android.chandchand.data.leagues.api

import com.android.chandchand.data.leagues.entity.StandingsServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LeaguesApi {

    @GET("leagueTable/{leagueId}")
    suspend fun getStandingsByLeague(
        @Path("leagueId") leagueId: Int
    ): Response<StandingsServerModel>
}