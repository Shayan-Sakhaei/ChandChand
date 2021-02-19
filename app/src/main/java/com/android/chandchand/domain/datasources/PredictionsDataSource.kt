package com.android.chandchand.domain.datasources

import com.android.chandchand.data.predictions.entity.PredictionsServerModel
import retrofit2.Response

interface PredictionsDataSource {
    suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel>
}