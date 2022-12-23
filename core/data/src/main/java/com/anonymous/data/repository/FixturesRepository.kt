package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities

interface FixturesRepository {
    suspend fun getFixtures(date: String): Result<List<FixtureEntity>>
    suspend fun getLiveFixtures(): Result<LiveFixtureEntities>
}