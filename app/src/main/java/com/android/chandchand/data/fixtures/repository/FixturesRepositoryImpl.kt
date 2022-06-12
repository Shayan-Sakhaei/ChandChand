package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.chandchand.domain.datasources.FixturesDataSource
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.repositories.FixturesRepository
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixturesDataSource,
    private val fixtureServerEntityMapper: FixtureServerEntityMapper,
    private val liveFixtureServerEntityMapper: LiveFixtureServerEntityMapper
) : com.android.domain.repositories.FixturesRepository {

    override suspend fun getFixtures(date: String): Result<List<com.android.domain.entities.FixtureEntity>> {
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

    override suspend fun getLiveFixtures(): Result<com.android.domain.entities.LiveFixtureEntities> {
        val response = remoteDataSource.getLiveFixtures()
        val body = response.body()
        return if (!response.isSuccessful || body == null) {
            Result.Error("")
        } else {
            val entities = body.api.fixtures.map {
                liveFixtureServerEntityMapper.map(it)
            }
            Result.Success(
                com.android.domain.entities.LiveFixtureEntities(
                    body.api.results,
                    entities
                )
            )
        }
    }
}