package com.anonymous.network.datasource

import com.anonymous.network.model.PredictionsServerModel
import retrofit2.Response

interface PredictionsDataSource {
    suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel>
}