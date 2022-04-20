package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFixturesRepository(
    private val fixtures: List<FixtureEntity>? = null,
    private val liveFixtures: LiveFixtureEntities? = null
) : FixturesRepository {

    override suspend fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>> =
        flow {
            if (fixtures == null) {
                emit(Result.Error("failed!"))
            } else {
                emit(Result.Success(fixtures))
            }
        }

    override suspend fun getLiveFixtures(): Flow<Result<LiveFixtureEntities>> =
        flow {
            if (liveFixtures == null) {
                emit(Result.Error("failed!"))
            } else {
                emit(Result.Success(liveFixtures))
            }
        }
}
