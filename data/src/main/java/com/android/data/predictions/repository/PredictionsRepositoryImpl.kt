package com.android.data.predictions.repository

import com.android.data.predictions.mapper.PredictionsServerEntityMapper
import com.android.domain.common.Result
import com.android.domain.entities.PredictionsEntity
import com.android.domain.repositories.PredictionsRepository
import javax.inject.Inject

class PredictionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: com.android.data.datasources.PredictionsDataSource,
    private val predictionsServerEntityMapper: PredictionsServerEntityMapper
) : PredictionsRepository {

    override suspend fun getPredictions(fixtureId: Int): Result<PredictionsEntity> {
        val response = remoteDataSource.getPredictions(fixtureId)
        val body = response.body()
        return if (!response.isSuccessful || body == null) {
            Result.Error("")
        } else {
            val entity = predictionsServerEntityMapper.map(body)
            Result.Success(entity)
        }
    }
}