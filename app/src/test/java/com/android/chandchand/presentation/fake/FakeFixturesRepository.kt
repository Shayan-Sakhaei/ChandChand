package com.android.chandchand.presentation.fake

import com.android.domain.common.Result
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.repositories.FixturesRepository

class FakeFixturesRepository(
    private val fixtures: List<FixtureEntity>? = null,
    private val liveFixtures: LiveFixtureEntities? = null
) : FixturesRepository {

    override suspend fun getFixtures(date: String): Result<List<FixtureEntity>> =
        if (fixtures == null) {
            Result.Error("failed!")
        } else {
            Result.Success(fixtures)
        }


    override suspend fun getLiveFixtures(): Result<LiveFixtureEntities> =
        if (liveFixtures == null) {
            Result.Error("failed!")
        } else {
            Result.Success(liveFixtures)
        }
}

