package com.android.chandchand.data.fixtures.api

import com.android.chandchand.data.fixtures.entity.FixturesServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FixturesApi {

    @GET("fixtures/date/{date}?timezone=Asia/Tehran")
    suspend fun getAllFixturesByDate(
        @Path("date") date: String
    ): Response<FixturesServerModel>
}