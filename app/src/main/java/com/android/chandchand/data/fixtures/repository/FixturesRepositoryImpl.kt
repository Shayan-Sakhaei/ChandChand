package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.chandchand.domain.datasources.FixturesDataSource
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixturesDataSource,
    private val fixtureServerEntityMapper: FixtureServerEntityMapper,
    private val liveFixtureServerEntityMapper: LiveFixtureServerEntityMapper
) : FixturesRepository {

    override fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>> {
        return flow {
            val response = remoteDataSource.getFixturesByDate(date)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Result.Error(""))
            } else {
                val entities = body.api.fixtures.map {
                    fixtureServerEntityMapper.map(it)
                }
                emit(Result.Success(entities))
            }
        }
    }

    override fun getLiveFixtures(): Flow<Result<LiveFixtureEntities>> {
        return flow {
            val response = remoteDataSource.getLiveFixtures()
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Result.Error(""))
            } else {
                val entities = body.api.fixtures.map {
                    liveFixtureServerEntityMapper.map(it)
                }
                emit(Result.Success(LiveFixtureEntities(body.api.results, entities)))
            }
        }
    }
}