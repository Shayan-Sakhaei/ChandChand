package com.android.data.fixtures.api

import com.android.data.fixtures.entity.FixturesServerModel
import com.android.data.fixtures.entity.LiveFixturesServerModel
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