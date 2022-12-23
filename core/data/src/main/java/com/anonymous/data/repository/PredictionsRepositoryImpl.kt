package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.PredictionsServerEntityMapper
import com.anonymous.data.model.PredictionsEntity
import com.anonymous.network.datasource.PredictionsDataSource
import javax.inject.Inject

class PredictionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PredictionsDataSource,
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