package com.android.domain.repositories

import com.android.domain.common.Result
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities

interface FixturesRepository {
    suspend fun getFixtures(date: String): Result<List<FixtureEntity>>
    suspend fun getLiveFixtures(): Result<LiveFixtureEntities>
}