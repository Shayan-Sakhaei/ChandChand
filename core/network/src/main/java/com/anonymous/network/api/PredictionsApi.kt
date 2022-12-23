package com.anonymous.network.api

import com.anonymous.network.model.PredictionsServerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PredictionsApi {
    @GET("predictions/{fixtureId}")
    suspend fun getPredictions(
        @Path("fixtureId") fixtureId: Int
    ): Response<PredictionsServerModel>
}