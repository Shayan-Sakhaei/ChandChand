package com.anonymous.network.api

import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FixturesApi {

    @GET("fixtures/date/{date}?timezone=Asia/Tehran")
    suspend fun getAllFixturesByDate(
        @Path("date") date: String
    ): Response<FixturesServerModel>

    @GET("fixtures/live/4640-4335-4378-4399-4346-4347")
    suspend fun getAllLiveFixtures(
    ): Response<LiveFixturesServerModel>
}