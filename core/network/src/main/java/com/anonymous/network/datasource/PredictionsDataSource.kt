package com.anonymous.network.datasource

import com.anonymous.network.model.PredictionsServerModel

interface PredictionsDataSource {
    suspend fun getPredictions(fixtureId: Int): Result<PredictionsServerModel>
}