package com.anonymous.data.repository.fake.test

import com.anonymous.common.result.Result
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.repository.FixturesRepository

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

