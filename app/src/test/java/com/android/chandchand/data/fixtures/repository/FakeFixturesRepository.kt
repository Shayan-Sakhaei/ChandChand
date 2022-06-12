package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFixturesRepository(
    private val fixtures: List<com.android.domain.entities.FixtureEntity>? = null,
    private val liveFixtures: com.android.domain.entities.LiveFixtureEntities? = null
) : com.android.domain.repositories.FixturesRepository {

    override fun getFixtures(date: String): Flow<Result<List<com.android.domain.entities.FixtureEntity>>> =
        flow {
            if (fixtures == null) {
                emit(Result.Error("failed!"))
            } else {
                emit(Result.Success(fixtures))
            }
        }

    override fun getLiveFixtures(): Flow<Result<com.android.domain.entities.LiveFixtureEntities>> =
        flow {
            if (liveFixtures == null) {
                emit(Result.Error("failed!"))
            } else {
                emit(Result.Success(liveFixtures))
            }
        }
}
