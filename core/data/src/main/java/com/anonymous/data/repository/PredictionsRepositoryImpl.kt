package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.PredictionsServerEntityMapper
import com.anonymous.data.model.PredictionsEntity
import com.anonymous.network.datasource.PredictionsDataSource
import com.anonymous.network.model.PredictionsServerModel
import javax.inject.Inject

class PredictionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PredictionsDataSource,
    private val predictionsServerEntityMapper: PredictionsServerEntityMapper
) : PredictionsRepository {

    override suspend fun getPredictions(fixtureId: Int): Result<PredictionsEntity> {
        val response = remoteDataSource.getPredictions(fixtureId)
        return response.fold(onSuccess = { model: PredictionsServerModel ->
            val entity = predictionsServerEntityMapper.map(model)
            Result.Success(entity)
        }, onFailure = { t: Throwable ->
            Result.Error(t.localizedMessage ?: "failed to get Response!")
        })
    }
}