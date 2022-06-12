package com.android.chandchand.data.predictions.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.predictions.mapper.PredictionsServerEntityMapper
import com.android.chandchand.domain.datasources.PredictionsDataSource
import com.android.domain.entities.PredictionsEntity
import com.android.domain.repositories.PredictionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PredictionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PredictionsDataSource,
    private val predictionsServerEntityMapper: PredictionsServerEntityMapper
) : com.android.domain.repositories.PredictionsRepository {

    override suspend fun getPredictions(fixtureId: Int): Flow<Result<com.android.domain.entities.PredictionsEntity>> {
        return flow {
            val response = remoteDataSource.getPredictions(fixtureId)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Result.Error(""))
            } else {
                val entity = predictionsServerEntityMapper.map(body)
                emit(Result.Success(entity))
            }
        }.flowOn(Dispatchers.IO)
    }
}