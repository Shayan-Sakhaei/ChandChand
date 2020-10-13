package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.domain.datasources.FixturesDataSource
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.repositories.FixturesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixturesDataSource,
    private val serverEntityMapper: FixtureServerEntityMapper,
) : FixturesRepository {

    override suspend fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>> {
        return flow {
            val response = remoteDataSource.getFixturesByDate(date)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Result.Error(""))
            } else {
                val entities = body.api.fixtures.map {
                    serverEntityMapper.map(it)
                }
                emit(Result.Success(entities))
            }
        }.flowOn(Dispatchers.IO)
    }
}