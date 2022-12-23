package com.anonymous.network.api

import com.anonymous.network.model.StandingsServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LeaguesApi {

    @GET("leagueTable/{leagueId}")
    suspend fun getStandingsByLeague(
        @Path("leagueId") leagueId: Int
    ): Response<StandingsServerModel>
}