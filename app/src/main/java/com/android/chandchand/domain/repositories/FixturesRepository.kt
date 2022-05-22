package com.android.chandchand.domain.repositories

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities

interface FixturesRepository {
    suspend fun getFixtures(date: String): Result<List<FixtureEntity>>
    suspend fun getLiveFixtures(): Result<LiveFixtureEntities>
}