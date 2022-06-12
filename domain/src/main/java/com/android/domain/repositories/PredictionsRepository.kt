package com.android.domain.repositories

import com.android.domain.entities.PredictionsEntity

interface PredictionsRepository {
    suspend fun getPredictions(fixtureId: Int): Result<PredictionsEntity>
}