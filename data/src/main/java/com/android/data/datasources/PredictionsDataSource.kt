package com.android.data.datasources

import com.android.data.predictions.entity.PredictionsServerModel
import retrofit2.Response

interface PredictionsDataSource {
    suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel>
}