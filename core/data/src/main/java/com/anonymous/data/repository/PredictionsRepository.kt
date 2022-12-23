package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.model.PredictionsEntity

interface PredictionsRepository {
    suspend fun getPredictions(fixtureId: Int): Result<PredictionsEntity>
}