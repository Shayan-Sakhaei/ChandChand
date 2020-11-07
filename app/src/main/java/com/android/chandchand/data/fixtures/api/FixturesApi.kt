package com.android.chandchand.data.fixtures.api

import com.android.chandchand.data.fixtures.entity.FixturesServerModel
import com.android.chandchand.data.fixtures.entity.LiveFixturesServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FixturesApi {

    @GET("fixtures/date/{date}?timezone=Asia/Tehran")
    suspend fun getAllFixturesByDate(
        @Path("date") date: String
    ): Response<FixturesServerModel>

    @GET("fixtures/live/923-2790-2833-2857-2755-2664")
    suspend fun getAllLiveFixtures(
    ): Response<LiveFixturesServerModel>
}