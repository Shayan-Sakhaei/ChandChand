package com.android.chandchand.presentation.fake

import com.anonymous.common.result.Result
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.repository.FixturesRepository

class FakeFixturesRepository(
    private val fixtures: List<com.anonymous.data.model.FixtureEntity>? = null,
    private val liveFixtures: com.anonymous.data.model.LiveFixtureEntities? = null
) : com.anonymous.data.repository.FixturesRepository {

    override suspend fun getFixtures(date: String): com.anonymous.common.result.Result<List<com.anonymous.data.model.FixtureEntity>> =
        if (fixtures == null) {
            com.anonymous.common.result.Result.Error("failed!")
        } else {
            com.anonymous.common.result.Result.Success(fixtures)
        }


    override suspend fun getLiveFixtures(): com.anonymous.common.result.Result<com.anonymous.data.model.LiveFixtureEntities> =
        if (liveFixtures == null) {
            com.anonymous.common.result.Result.Error("failed!")
        } else {
            com.anonymous.common.result.Result.Success(liveFixtures)
        }
}

