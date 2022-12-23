package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.FixtureServerEntityMapper
import com.anonymous.data.mapper.LiveFixtureServerEntityMapper
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.network.datasource.FixturesDataSource
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixturesDataSource,
    private val fixtureServerEntityMapper: FixtureServerEntityMapper,
    private val liveFixtureServerEntityMapper: LiveFixtureServerEntityMapper
) : FixturesRepository {

    override suspend fun getFixtures(date: String): Result<List<FixtureEntity>> {
        val response = remoteDataSource.getFixturesByDate(date)
        val body = response.body()
        return if (!response.isSuccessful || body == null) {
            Result.Error("")
        } else {
            val entities = body.api.fixtures.map {
                fixtureServerEntityMapper.map(it)
            }
            Result.Success(entities)
        }
    }

    override suspend fun getLiveFixtures(): Result<LiveFixtureEntities> {
        val response = remoteDataSource.getLiveFixtures()
        val body = response.body()
        return if (!response.isSuccessful || body == null) {
            Result.Error("")
        } else {
            val entities = body.api.fixtures.map {
                liveFixtureServerEntityMapper.map(it)
            }
            Result.Success(
                LiveFixtureEntities(
                    body.api.results,
                    entities
                )
            )
        }
    }
}