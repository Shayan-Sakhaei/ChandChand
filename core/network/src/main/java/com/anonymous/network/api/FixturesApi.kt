package com.anonymous.network.api

import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel
import retrofit2.http.GET
import retrofit2.http.Path

interface FixturesApi {

    @GET("fixtures/date/{date}?timezone=Asia/Tehran")
    suspend fun getAllFixturesByDate(
        @Path("date") date: String
    ): Result<FixturesServerModel>

    @GET("fixtures/live/5505-5267-5284-5367-5347-5322")
    suspend fun getAllLiveFixtures(
    ): Result<LiveFixturesServerModel>
}