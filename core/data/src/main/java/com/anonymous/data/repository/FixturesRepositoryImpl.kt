package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.FixtureServerEntityMapper
import com.anonymous.data.mapper.LiveFixtureServerEntityMapper
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.network.datasource.FixturesDataSource
import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixturesDataSource,
    private val fixtureServerEntityMapper: FixtureServerEntityMapper,
    private val liveFixtureServerEntityMapper: LiveFixtureServerEntityMapper
) : FixturesRepository {

    override suspend fun getFixtures(date: String): Result<List<FixtureEntity>> {
        val response = remoteDataSource.getFixturesByDate(date)
        return response.fold(
            onSuccess = { model: FixturesServerModel ->
                val entities = model.api.fixtures.map { fixtureServerEntityMapper.map(it) }
                Result.Success(entities)
            },
            onFailure = { t: Throwable ->
                Result.Error(t.localizedMessage ?: "failed to get Response!")
            }
        )
    }

    override suspend fun getLiveFixtures(): Result<LiveFixtureEntities> {
        val response = remoteDataSource.getLiveFixtures()
        return response.fold(
            onSuccess = { model: LiveFixturesServerModel ->
                val entities = model.api.fixtures.map { liveFixtureServerEntityMapper.map(it) }
                Result.Success(
                    LiveFixtureEntities(model.api.results, entities)
                )
            },
            onFailure = { t: Throwable ->
                Result.Error(t.localizedMessage ?: "failed to get Response!")
            }
        )
    }
}