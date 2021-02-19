package com.android.chandchand.domain.repositories

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.PredictionsEntity
import kotlinx.coroutines.flow.Flow

interface PredictionsRepository {
    suspend fun getPredictions(fixtureId: Int): Flow<Result<PredictionsEntity>>
}